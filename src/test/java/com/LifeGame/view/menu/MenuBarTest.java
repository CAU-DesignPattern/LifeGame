package com.LifeGame.view.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuBarTest {

    private MenuBar menuBar;

    @BeforeEach
    void setUp() {
        this.menuBar = new MenuBar();
    }

    @Test
    @DisplayName("addMenu: JMenu 추가 테스트")
    void addMenuTest() {

        //given
        String name = "test menu";

        //when
        this.menuBar.addMenu(name);

        //then
        assertEquals(name, this.menuBar.getMenu(name).getText());
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