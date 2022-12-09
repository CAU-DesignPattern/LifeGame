package com.LifeGame.controller.drawBehavior;

import com.LifeGame.model.Model;
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
class PatternDrawBehaviorTest {

    @Mock
    private Model model;
    @Mock
    private PatternModel patternModel;
    @InjectMocks
    private PatternDrawBehavior patternDrawBehavior;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[draw] draw 호출 시 model의 toggle 메서드가 잘 호출되는지 테스트")
    void drawTest() {

        //given
        int x = 0;
        int y = 0;

        //when
        this.patternDrawBehavior.draw(x, y);

        //then
        verify(this.model).draw(this.patternModel.getBluePrint(), x, y);
    }
}