package com.LifeGame.view;

import com.LifeGame.controller.PaletteController;
import com.LifeGame.model.PatternModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PatternImagePanelTest {

    private PaletteController paletteController;

    private PatternImagePanel patternImagePanel;

    private int id;
    private Image image;
    private int[][] bluePrint;

    @BeforeEach
    void setUp() throws IOException {
        this.paletteController = Mockito.mock(PaletteController.class);

        this.id = 1;
        this.image = Mockito.mock(Image.class);
        this.bluePrint = new int[][] {{0}};

        this.patternImagePanel = new PatternImagePanel(this.paletteController, this.id, this.image, this.bluePrint);
    }

    @Test
    @DisplayName("[update/same] 업데이트 상황에 따라 border 생성이 잘 만들어지는지 테스트")
    void updateSameTest() {

        //given
        PatternModel patternModel = Mockito.mock(PatternModel.class);
        when(patternModel.getId()).thenReturn(this.id);

        //when
        this.patternImagePanel.update(patternModel, null);

        //then
        assertTrue(this.patternImagePanel.getBorder() instanceof LineBorder);
    }

    @Test
    @DisplayName("[update/different] 업데이트 상황에 따라 border 생성이 잘 만들어지는지 테스트")
    void updateDifferentTest() {

        //given
        PatternModel patternModel = Mockito.mock(PatternModel.class);
        when(patternModel.getId()).thenReturn(2);

        //when
        this.patternImagePanel.update(patternModel, null);

        //then
        assertNull(this.patternImagePanel.getBorder());
    }
}