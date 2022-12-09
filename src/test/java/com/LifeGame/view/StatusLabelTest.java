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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatusLabelTest {

    @Mock
    private LifeController lifeController;
    @InjectMocks
    private StatusLabel statusLabel;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[update] 업데이트 시 label이 잘 업데이트되는지 테스트")
    void updatePatternTest() {

        //given
        int[][] map = {{0, 1, 0}, {0, 0, 0}, {1, 1, 1}};
        Model model = Mockito.mock(Model.class);
        when(model.getMapSize()).thenReturn(3);
        when(model.getMap()).thenReturn(map);

        //when
        this.statusLabel.update(model, null);

        //then
        assertEquals(0, this.statusLabel.getGeneration());
        assertEquals(4, this.statusLabel.getLiveCells());
    }
}