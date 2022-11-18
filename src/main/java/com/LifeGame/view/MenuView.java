package com.LifeGame.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;


/*** ****************************************************************
 *  A MenuSite is a frame that holds a menu bar.
 *  Other objects in
 *	the system (which do not have to be visual objects) can negotiate
 *	with the MenuSite to have menu's placed on the site's menu
 *  bar (or within submenus already found on the menu bar). These
 *  objects can easily remove the menu modifications they've made.
 *  <p>
 *  The MenuSite is a static Singleton. You cannot create one
 *  with <code>new</code>---It's made up entirely of <code>static</code>
 *  methods which you can access globally.
 *	<p>
 *  The first thing you must do is tell the system
 *  which JFrame will host the menu bar by calling
 *  {@link #establish MenuSite.establish(mainFrame)}. For example:
 *  <PRE>
 *  public class mainFrame extends JFrame
 *  {	public mainFrame()
 *    {	MenuSite.establish( this );
 *			//...
 *    }
 *  }
 *  </PRE>
 *  Once a menu site is established,
 *  objects that create user interfaces can add items
 *  to the menu site by calling {@link #addLine addLine(...)}
 *  (or more rarely, {@link //#addMenu addMenu(...)}). In the
 *  following example, an <code>EmployeeManager</code> object
 *  adds an "Employee" menu that has two submenus: "Hire" and
 *  "Fire"
 *  <PRE>
 *  public class EmployeeManager
 *  {
 *  	public EmployeeManager()
 *    {	MenuSite.addLine( this, "Employee",		 null )
 *  		MenuSite.addLine( this, "Employee:Hire," HireListener );
 *  		MenuSite.addLine( this, "Employee:Fire," FireListener );
 *    }
 *
 *  }
 *  </PRE>
 *	The <code>HireListener</code> and <code>FireListener</code>
 *	objects implement {@link java.awt.event.ActionListener}, and
 *	are notified when the menu item is selected.
 *	<p>
 *  There's (deliberatly) no way to remove individual menu items.
 *  When the object that added items shuts down it's user interface,
 *  it issues a single call to {@link //#removeMyMenus removeMyMenus}
 *  to remove <i>all</i> the menus and line items it added.
 *  <p>
 *  The creating object can also issue a
 *  {@link //#setEnable} call to disable (or enable) all
 *  menu and menus it created.
 *  <p><b>Important:</b> A referece to the object that asked for
 *  the menu item to be inserted is passed into most of the methods
 *  of this class as the <code>requester</code> argument. The
 *  requester is used as a "key" in a hash table, and the hash-table
 *  lookup method uses both the object's <code>equals()</code> and
 *  <code>hashcode()</code> methods to do the lookup. For things
 *  to work properly, the object used as a requester should
 *  not implement <code>equals()</code>. Java visual objects
 *  like JComponent work fine as a requester, but be careful if
 *  you use an object of a class of your own devising. Solve the
 *  problem as follows:
 *  <PRE>
 *  class MyClass
 *  {	public boolean equals() { ... }
 *
 *		private final Object requesterId = new Object();
 *		public f()
 *        {	// Use requesterId instead of "this."
 *			MenuSite.addLine( requesterId, ... );
 *        }
 *    }
 *  </PRE>
 *	<p>If you haven't worked with menus before, bear in mind that
 *	menu items and menus have both a "name" and also a "label."
 *	The label is visible to the program's user, the name is
 *	an arbitrary internal string. In an internationalized application,
 *	the label will change with the local, but the name will
 *	be fixed. The current classes use only the menu "name." If you don't
 *	do anything special, the name is used as the label. You can provide
 *	a file that maps names to arbitrary strings (and also defines
 *	menu shortcuts) by calling {@link //#mapNames mapNames(...)}.
 *
 *  @include /etc/license.txt
 */

public final class MenuView {
    private static JFrame menuFrame = null;
    private static JMenuBar menuBar = null;

    /*** The "requesters" table keeps track of who requested which
     * menu items. It is indexed by requester and contains a
     * Set of MenuSite.Item objects that identify all
     * items added by that requester.
     */
    private static Map requesters = new HashMap();

    /*** Maps "names" to the visible labels that actually
     *  appear on the screen.
     */
    private static Properties nameMap;

    /*** Regular expression for extracting shortcuts from the
     *  name-to-label map. The input syntax is the string to
     *  the right of the = in the input line:
     *  <code>
     *  name = label; optional shortcut
     *  </code>
     *  This regular expression strips out leading and trailing
     *  whitespace and all whitespace that surrounds the semicolon.
     *  After matching, group(1) holds the label and group(2) either
     *  holds the shortcut or is null if no shortcut is specified.
     */
    private static Pattern shortcutExtractor = Pattern.compile("\\s*([^;]+?)\\s*"                // value
            + "(;\\s*([^\\s].*?))?\\s*$");    // ; shortcut

    /*** Isolate the menu names. Given an input string of the
     *  form "one:two:three:four", after matching
     *  group(1) holds "one", group(2) holds "two", etc.
     *  up to 7 colons are recognized.
     */
    private static Pattern submenuExtractor = Pattern.compile("(.*?)(?::(.*?))?" + "(?::(.*?))?" + "(?::(.*?))?" + "(?::(.*?))?" + "(?::(.*?))?" + "(?::(.*?))?");

    /*** The menuBarContents list contains references to the
     *	various menus that comprise the menu bar. This kludge is
     *	necessary because Swing does not really support the notion
     *	of a Help menu (though it claims to do so). In
     *	particular, Swing won't let you insert menus anywhere
     *	other than the far right of the menu bar, where the Help
     *	menu should be. Removing the help menu and then adding it
     *	back doesn't work reliably in all (or any?) Swing versions,
     *	either. Consequently, when a new menu is added to
     *	a menu bar, you need to clear out the existing menu bar
     *	and rebuild it from scratch. This list holds the menu
     *	items that comprise the menu bar in the order that they
     *	will appear on the screen (left to right).
     */

    private static final LinkedList menuBarContents = new LinkedList();

    /*** ***************************************************************
     * MenuSite is a singleton. A private constructor prevents
     * you from manufacturing one using <code>new</code>.
     */
    private MenuView() {
    }

    /*** Check the current object for validity. If you use this
     *  method in an assertion ("assert valid()") then it will
     *  be removed when assetions are enabled.
     *
     *  @throws //AssertionException if the menu hasn't been established.
     */

    private static boolean valid() {
        assert menuFrame != null : "MenuSite not established";
        assert menuBar != null : "MenuSite not established";
        return true;
    }

    /*** ***************************************************************
     * Establish a JFrame as the program's menu site. This method must
     * be called before any of the other menu-site methods may be called.
     * (Most of these will throw a {@link NullPointerException}
     * if you try to use them when no menu site has been established.)
     */
    public synchronized static void establish(JFrame container) {
        assert container != null;
        assert menuFrame == null : "Tried to establish more than one MenuSite";

        menuFrame = container;
        menuFrame.setJMenuBar(menuBar = new JMenuBar());

        assert valid();
    }

    /*** **************************************************************
     *  Adds a line item to a menu.
     *  The menu is created if it does not already exist.
     *  <p>
     *  This method is the preferred way to both create menus and
     *  add line items to existing menus.
     *  See {@link //#addMenu addMenu(...)} for the
     *  rules of menu creation.
     *  <p>
     *  By default, the "name" is used for the "label." However,
     *  when there is a name map (see {@link //#mapNames}), then the name
     *  parameter is used for the name, and the associated labels and
     *  shortcuts specified in the map are used.
     *  If there is a map, but the map has no
     *  entry for the item named by the <code>name</code> parameter,
     *  then the name is used for the label and a warning is logged to the
     *  com.holub.ui stream using the standard java Logging APIs.
     *
     *  @param requester    The object that requested that this
     *  					line item be added.
     *
     *  @param name        The (hidden) name text for this item.
     *  				When there's no {@linkplain //#mapNames name map},
     *  				the same string is used for	both the name and the
     *  				label (and there is no shortcut), otherwise
     *  				the <code>name</code> argument specifies the
     *  				name only, and the associated label
     *  				(and shortcut) is taken	from the map.
     *  				<p>
     *  				Use the name <code>"-"</code> to place a separator
     *  				into a menu. The <code>listener</code> argument
     *  				is not used in this case, and can be null.
     *
     *  @param toThisMenu The specifier of the menu to which you're adding
     *  				  the line item.
     *  				  (See {@link //#addMenu addMenu(...)}
     *  				  for a discussion of specifiers.) The
     *  				  specified menu is created if it doesn't
     *  				  already exist.
     *
     *  @param listener    The ActionListener to notify when the menu
     *  				item is selected.
     *
     *    @see //#addMenu
     *  @see //#mapNames
     */
    public static void addLine(Object requester, String toThisMenu, String name, ActionListener listener) {
        assert requester != null : "null requester";
        assert name != null : "null item";
        assert toThisMenu != null : "null toThisMenu";
        assert valid();

        // The "element" field is here only so that we don't create
        // a menu if the assertion in the else clause fires.
        // Otherwise, we could just create the items in the
        // if and else clauses.

        Component element;

        if (name.equals("-")) {
            element = new JSeparator();
        } else {

            assert listener != null : "null listener";

            JMenuItem lineItem = new JMenuItem(name);
            lineItem.setName(name);
            lineItem.addActionListener(listener);
            setLabelAndShortcut(lineItem);

            element = lineItem;
        }

        JMenu found = createSubmenuByName(requester, toThisMenu);
        if (found == null) {
            throw new IllegalArgumentException("addLine() can't find menu (" + toThisMenu + ")");
        }

        Item item = new Item(element, found, toThisMenu);
        menusAddedBy(requester).add(item);
        item.attachYourselfToYourParent();
    }

    //===========================================================
    // 			Private support methods and classes				|
    //===========================================================

    /*** **************************************************************
     * Find the submenu specified by <code>specifier</code>. If it
     * doesn't exist, create it.
     * @see //#addMenu
     */
    private static JMenu createSubmenuByName(Object requester, String menuSpecifier) {
        assert requester != null;
        assert menuSpecifier != null;
        assert valid();

        Matcher m = submenuExtractor.matcher(menuSpecifier);
        if (!m.matches()) throw new IllegalArgumentException("Malformed menu specifier.");
        // If it's null, then start the search at the menu bar,
        // otherwise start the search at the menu addressed by "parent"

        JMenuItem child = null;
        MenuElement parent = menuBar;
        String childName;

        for (int i = 1; (childName = m.group(i++)) != null; parent = child) {
            child = getSubmenuByName(childName, parent.getSubElements());

            if (child != null) {
                if (!(child instanceof JMenu))    // it's a line item!
                    throw new IllegalArgumentException("Specifier identifes line item, not menu.");
            } else // it doesn't exist, create it
            {
                child = new JMenu(childName);
                child.setName(childName);
                setLabelAndShortcut(child);

                Item item = new Item(child, parent, menuSpecifier);
                menusAddedBy(requester).add(item);
                item.attachYourselfToYourParent();
            }
        }

        return (JMenu) child; // the earlier instanceof guarantees safety
    }

    /*** Return the JMenu with the given name from the menu bar
     *  or null if there is no such item.
     *  This method doesn't understand colon-delimited specifiers. It
     *  searches the level "contained" in the parent for an item with
     *  the given "name."
     */

    private static JMenuItem getSubmenuByName(String name, MenuElement[] contents) {
        JMenuItem found = null;
        for (int i = 0; found == null && i < contents.length; ++i) {
            // This is not documented, but the system creates internal
            // popup menus for empty submenus. If we come across one of
            // these, then look for "name" in the popup's contents. This
            // would be a lot easier if PopupMenu and JMenuItem
            // implemented a common interface, but they don't.
            // I can't use a class adapter to make them appear to
            // implement a common interface because the JPopupWindows
            // are manufactured by Swing, not by me.

            if (contents[i] instanceof JPopupMenu)
                found = getSubmenuByName(name, ((JPopupMenu) contents[i]).getSubElements());

            else if (((JMenuItem) contents[i]).getName().equals(name)) found = (JMenuItem) contents[i];
        }
        return found;
    }

    /*** ****************************************************************
     *  This method modifies the incoming item to hold the label
     *  and shortcut specified in name map
     *  (see {@link //#mapNames mapNames(...)}).
     *  Normally, you would not call this method. Instead, you'd
     *  insert items using
     *  {@link #addLine addLine(...)},
     *  which calls this method internally.
     *  You may also call this method directly if you
     *  intend to use
     *  {@link #addLine addLine(...)} to insert
     *  menu items.
     *	<p>
     *  The current
     *  method does nothing if (1) there's no map or (2) the
     *  name parameter isn't a key in the map. The incoming item
     *  must have a name or label. If it has only a label, then
     *  the name is set to the label and the label is replaced
     *  by whatever label is found in the index file.
     *  The method also silently does nothing if the incoming
     *  <code>JMenuItem</code> does not have a name.
     *
     *  @see //#mapNames
     *  @see #addLine
     */
    private static void setLabelAndShortcut(JMenuItem item) {
        String name = item.getName();
        if (name == null) return;

        String label;
        if (nameMap != null && (label = (String) (nameMap.get(name))) != null) {
            Matcher m = shortcutExtractor.matcher(label);
            if (!m.matches())    // Malformed input line
            {
                item.setText(name);
                Logger.getLogger("com.holub.ui").warning("Bad " + "name-to-label map entry:" + "\n\tinput=[" + name + "=" + label + "]" + "\n\tSetting label to " + name);
            } else {
                item.setText(m.group(1));

                String shortcut = m.group(3);

                if (shortcut != null) {
                    if (shortcut.length() == 1) {
                        item.setAccelerator(KeyStroke.getKeyStroke(shortcut.toUpperCase().charAt(0), Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
                    } else {
                        KeyStroke key = KeyStroke.getKeyStroke(shortcut);
                        if (key != null) item.setAccelerator(key);
                        else {
                            Logger.getLogger("com.holub.ui").warning("Malformed shortcut parent specification " + "in MenuSite map file: " + shortcut);
                        }
                    }
                }
            }
        }
    }

    /*** *********************************************************
     * Return a Collection of menu items associated with a given
     * requester. A new (empty) Collection is created and returned
     * if there are no menus associated with the requester at
     * present.
     */
    private static Collection menusAddedBy(Object requester) {
        assert requester != null : "Bad argument";
        assert requesters != null : "No requesters";
        assert valid();

        Collection menus = (Collection) (requesters.get(requester));
        if (menus == null) {
            menus = new LinkedList();
            requesters.put(requester, menus);
        }
        return menus;
    }

    /*** ***********************************************************
     * An Item makes the association between a line item or
     * submenu and the MenuBar or Menu that contains it. You can
     * ask an Item to add or remove itself from its container.
     * All the weirdness associated with help menus is handled
     * here.
     */
    private static final class Item {
        // private JMenuItem  item;
        private Component item;

        private String parentSpecification; // of JMenu or of
        // JMenuItem's parent
        private MenuElement parent;                 // JMenu or JMenuBar
        private boolean isHelpMenu;

        public String toString() {
            StringBuffer b = new StringBuffer(parentSpecification);
            if (item instanceof JMenuItem) {
                JMenuItem i = (JMenuItem) item;
                b.append(":");
                b.append(i.getName());
                b.append(" (");
                b.append(i.getText());
                b.append(")");
            }
            return b.toString();
        }

        /*------------------------------------------------------------*/

        private boolean valid() {
            assert item != null : "item is null";
            assert parent != null : "parent is null";
            return true;
        }

        /*** Create a new Item. If the JMenuItem's name is the
         *  string "help" then it's assumed to be the help menu and
         *  is treated specially. Note that several help menus can
         *  be added to a site: They'll be stacked up at the far
         *  right in the reverse order of addition. Similarly
         *  file menus are stacked up at the far left.
         *
         *  @param item         the item being added
         *  @param parent     The menu bar or a menu that
         *  				 contains the current item. Must
         *  				 be a JMenuBar or a JMenu.
         */

        public Item(Component item, MenuElement parent, String parentSpecification) {
            assert parent != null;
            assert parent instanceof JMenu || parent instanceof JMenuBar : "Parent must be JMenu or JMenuBar";

            this.item = item;
            this.parent = parent;
            this.parentSpecification = parentSpecification;
            this.isHelpMenu = (item instanceof JMenuItem) && (item.getName().compareToIgnoreCase("help") == 0);

            assert valid();
        }

        /*** ******************************************************
         * Attach a menu item to it's parent (either a menu
         * bar or a menu). Items are added at the end of the
         * <code>menuBarContents</code> list unless a help
         * menu exists, in which case items are added at
         * the penultimate position.
         */

        public void attachYourselfToYourParent() {
            assert valid();

            if (parent instanceof JMenu) {
                ((JMenu) parent).add(item);
            } else if (menuBarContents.size() <= 0) {
                menuBarContents.add(this);
                ((JMenuBar) parent).add(item);
            } else {
                Item last = (Item) (menuBarContents.getLast());
                if (!last.isHelpMenu) {
                    menuBarContents.addLast(this);
                    ((JMenuBar) parent).add(item);
                } else    // remove the help menu, add the new
                {        // item, then put the help menu back
                    // (following the new item).

                    menuBarContents.removeLast();
                    menuBarContents.add(this);
                    menuBarContents.add(last);

                    if (parent == menuBar) parent = regenerateMenuBar();
                }
            }
        }

        /*** ******************************************************
         * Replace the old menu bar with a new one that reflects
         * the current state of the <code>menuBarContents</code>
         * list.
         */
        private JMenuBar regenerateMenuBar() {
            assert valid();

            // Create the new menu bar and populate it from
            // the current-contents list.

            menuBar = new JMenuBar();
            ListIterator i = menuBarContents.listIterator(0);
            while (i.hasNext()) menuBar.add(((Item) (i.next())).item);

            // Replace the old menu bar with the new one.
            // Calling setVisible causes the menu bar to be
            // redrawn with a minimum amount of flicker. Without
            // it, the redraw doesn't happen at all.

            menuFrame.setJMenuBar(menuBar);
            menuFrame.setVisible(true);
            return menuBar;
        }
    }
}
