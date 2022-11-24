package com.LifeGame.controller;

import com.LifeGame.controller.action.Action;
import com.LifeGame.view.View;
import com.LifeGame.view.menu.MenuBar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest {

    @Mock
    private View view;
    @Mock
    private MenuBar menuBar;
    @InjectMocks
    private MenuController menuController;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[addMenuItem] MenuBar의 적절한 메서드를 호출하고 파라미터가 잘 넘어가는지 테스트")
    void addMenuItemTest() {

        //given
        String menu = "test menu";
        String menuItem = "test menu item";
        Action action = Mockito.mock(Action.class);

        //when
        this.menuController.addMenuItem(menu, menuItem, action);

        //then
        verify(this.menuBar).addMenu(menu, menuItem, action);
    }
}