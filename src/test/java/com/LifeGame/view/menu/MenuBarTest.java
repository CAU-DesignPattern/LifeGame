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
class MenuBarTest {

    @Mock
    private MenuController menuController;
    @InjectMocks
    private MenuBar menuBar;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("addMenu: JMenu 추가 테스트")
    void addMenuTest() {

        //given
        String name = "test menu";
        String menuItem = "test menu item";

        //when
        this.menuBar.addMenu(name, menuItem);

        //then
        assertEquals(name, this.menuBar.getMenu(name).getText());
        assertEquals(menuItem, this.menuBar.getMenu(name).getMenuItem(menuItem).getText());
    }

    @Test
    @DisplayName("removeMenu: JMenu 삭제 테스트")
    void removeMenuTest() {

        //given
        String name = "test menu";
        this.menuBar.addMenu(name);

        //when
        this.menuBar.removeMenu(name);

        //then
        assertNull(this.menuBar.getMenu(name));
    }

    @Test
    @DisplayName("changeName: JMenu 이름 변경 테스트")
    void changeNameTest() {

        //given
        String name = "test menu";
        this.menuBar.addMenu(name);

        //when
        String newName = "changed name";
        this.menuBar.changeName(name, newName);

        //then
        assertNull(this.menuBar.getMenu(name));
        assertEquals(newName, this.menuBar.getMenu(newName).getText());
    }

    @Test
    @DisplayName("getMenu: JMenu getter 테스트")
    void getMenuTest() {

        //given
        String name = "test menu";

        //when
        this.menuBar.addMenu(name);

        //then
        assertEquals(name, this.menuBar.getMenu(name).getText());
    }
}