package com.LifeGame.view.menu;

import com.LifeGame.controller.MenuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MenuTest {

    @Mock
    private MenuController menuController;
    @InjectMocks
    private Menu menu;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("addMenuItem: JMenuItem 추가 테스트")
    void addMenuItemTest() {

        //given
        String name = "test menu item";

        //when
        this.menu.addMenuItem(name);

        //then
        assertEquals(name, this.menu.getMenuItem(name).getText());
    }

    @Test
    @DisplayName("removeMenuItem: JMenuItem 삭제 테스트")
    void removeMenuItemTest() {

        //given
        String name = "test menu item";
        this.menu.addMenuItem(name);

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
        this.menu.addMenuItem(name);

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
        this.menu.addMenuItem(name);

        //then
        assertEquals(name, this.menu.getMenuItem(name).getText());
    }
}