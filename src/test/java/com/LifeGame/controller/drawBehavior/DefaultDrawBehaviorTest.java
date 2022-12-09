package com.LifeGame.controller.drawBehavior;

import com.LifeGame.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultDrawBehaviorTest {

    @Mock
    private Model model;
    @InjectMocks
    private DefaultDrawBehavior defaultDrawBehavior;

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
        this.defaultDrawBehavior.draw(x, y);

        //then
        verify(this.model).toggle(x, y);
    }
}