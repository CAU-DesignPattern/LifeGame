package com.LifeGame.controller;

import com.LifeGame.model.PatternModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaletteControllerTest {

    @Mock
    private PatternModel patternModel;
    @InjectMocks
    private PaletteController paletteController;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[clickAction] action 기능 테스트")
    void clickActionTest() {

        //given
        int id = 0;
        int[][] bluePrint = new int[][] {{0}};
        //when
        this.paletteController.clickAction(id, bluePrint);

        //then
        verify(this.patternModel).changePattern(id, bluePrint);
    }
}