package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import com.LifeGame.model.Model;
import com.LifeGame.service.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreActionTest {

    @Mock
    private Service service;
    @Mock
    private Model model;
    @Mock
    private MenuController menuController;
    @InjectMocks
    private StoreAction storeAction;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[action] action 기능 테스트")
    void actionTest() {

        //given
        int[][] map = {{0, 0}};
        int mapSize = 0;
        when(this.model.getMap()).thenReturn(map);
        when(this.model.getMapSize()).thenReturn(mapSize);

        //when
        this.storeAction.action();

        //then
        verify(this.service).store(argThat(mapData -> {
            Assertions.assertEquals(mapSize, mapData.getMapSize());
            Assertions.assertEquals(map, mapData.getMap());
            return true;
        }));
    }
}