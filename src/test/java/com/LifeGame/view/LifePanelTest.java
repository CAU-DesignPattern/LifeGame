package com.LifeGame.view;

import com.LifeGame.controller.LifeController;
import com.LifeGame.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LifePanelTest {

    @Mock
    private LifeController lifeController;
    @Mock
    private Cell outermostCell;
    //@Spy
    @InjectMocks
    private LifePanel lifePanel;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[update] update가 잘 기능하는지 테스트")
    void updateTest() {

        //given
        int[][] cells = new int[64][64];
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                cells[i][j] = 1;
            }
        }
        Rectangle bounds = Mockito.mock(Rectangle.class);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 512;
        this.lifePanel.setBounds(bounds);
        Model model = Mockito.mock(Model.class);
        when(model.getMap()).thenReturn(cells);

        //when
        this.lifePanel.update(model, null);

        //then
        verify(this.outermostCell).clear();
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                assertEquals(cells[i][j] == 1, ((Resident) this.lifePanel.getOutermostCell().getCell(i, j)).getAmAlive());
            }
        }
        verify(this.lifePanel).repaint();
    }
}
