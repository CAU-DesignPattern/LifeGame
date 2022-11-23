package com.LifeGame.view.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        this.menu = new Menu("test menu");
    }

    @Test
    @DisplayName("addMenuItem: JMenuItem 추가 테스트")
    void addMenuItemTest() {

        //given
        String name = "test menu item";

        //when
        this.menu.addMenuItem(name, null);

        //then
        assertEquals(name, this.menu.getMenuItem(name).getText());
    }

    @Test
    @DisplayName("removeMenuItem: JMenuItem 삭제 테스트")
    void removeMenuItemTest() {

        //given
        String name = "test menu item";
        this.menu.addMenuItem(name, null);

        //when
        this.menu.removeMenuItem(name);

        //then
        assertNull(this.menu.getMenuItem(name));
    }

    @Test
    @DisplayName("changeName: JMenuItem 이름 변경 테스트")
    void changeNameTest() {

        //given
        String name = "test menu item";
        this.menu.addMenuItem(name, null);

        //when
        String newName = "changed name";
        this.menu.changeName(name, newName);

        //then
        assertNull(this.menu.getMenuItem(name));
        assertEquals(newName, this.menu.getMenuItem(newName).getText());
    }

    @Test
    @DisplayName("getMenuItem: JMenuItem getter 테스트")
    void getMenuItemTest() {

        //given
        String name = "test menu item";

        //when
        this.menu.addMenuItem(name, null);

        //then
        assertEquals(name, this.menu.getMenuItem(name).getText());
    }
}