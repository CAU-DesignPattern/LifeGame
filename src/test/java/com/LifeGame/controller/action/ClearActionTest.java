package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import com.LifeGame.model.Model;
import com.LifeGame.view.LifePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClearActionTest {

    @Mock
    private LifePanel lifePanel;
    @Mock
    private Model model;
    @Mock
    private MenuController menuController;
    @InjectMocks
    private ClearAction clearAction;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[constructor] menuController의 addMenuItem 메서드를 호출하고 파라미터가 잘 넘어가는지 테스트")
    void constructorTest() {

        //given
        String menu = "Grid";
        String menuItem = "Clear";

        //when
        //then
        verify(this.menuController).addMenuItem(menu, menuItem, this.clearAction);
    }

    @Test
    @DisplayName("[action] action 기능 테스트")
    void actionTest() {

        //given
        //when
        this.clearAction.action();

        //then
        verify(this.lifePanel).clearCell();
        verify(this.lifePanel).repaint();
        verify(this.model).clearMap();
    }
}