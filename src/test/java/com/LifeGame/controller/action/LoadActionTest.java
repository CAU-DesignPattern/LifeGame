package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import com.LifeGame.model.Model;
import com.LifeGame.service.Service;
import com.LifeGame.view.Cell;
import com.LifeGame.view.LifePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadActionTest {

    @Mock
    private Service service;
    @Mock
    private LifePanel lifePanel;
    @Mock
    private Model model;
    @Mock
    private MenuController menuController;
    @InjectMocks
    private LoadAction loadAction;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[constructor] menuController의 addMenuItem 메서드를 호출하고 파라미터가 잘 넘어가는지 테스트")
    void constructorTest() {

        //given
        String menu = "Grid";
        String menuItem = "Load";
        Action action = this.loadAction;

        //when
        //then
        verify(this.menuController).addMenuItem(menu, menuItem, action);
    }

    @Test
    @DisplayName("[action/try] action 기능 테스트")
    void actionTryTest() {
        //given
        int[][] liveCells = {{0, 0}};
        when(this.service.load()).thenReturn(liveCells);

        Rectangle bounds = Mockito.mock(Rectangle.class);
        when(this.lifePanel.getBounds()).thenReturn(bounds);

        Cell cell = Mockito.mock(Cell.class);
        when(this.lifePanel.getOutermostCell()).thenReturn(cell);

        //when
        this.loadAction.action();

        //then
        verify(cell).userClicked(new Point(liveCells[0][0], liveCells[0][1]), bounds);
        verify(this.lifePanel).repaint();
        verify(this.model).clearMap();
        verify(this.model).toggle(liveCells);
    }

    @Test
    @DisplayName("[action/catch] action 기능 테스트")
    void actionCatchTest() {

        //given
        when(this.service.load()).thenReturn(null);

        //when
        this.loadAction.action();

        //then
        verify(this.lifePanel).repaint();
        verify(this.model).clearMap();
    }
}