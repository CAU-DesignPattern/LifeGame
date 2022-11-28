package com.LifeGame.controller;

import com.LifeGame.controller.action.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest {

    @Mock
    private ClearAction clearAction;
    @Mock
    private LoadAction loadAction;
    @Mock
    private StoreAction storeAction;
    @Mock
    private ExitAction exitAction;
    @Mock
    private HaltAction haltAction;
    @Mock
    private TickAction tickAction;
    @Mock
    private AgonizingAction agonizingAction;
    @Mock
    private SlowAction slowAction;
    @Mock
    private MediumAction mediumAction;
    @Mock
    private FastAction fastAction;
    @InjectMocks
    private MenuController menuController;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[action] action 기능 테스트")
    void actionTest() {

        //given
        HashMap<String, Action> gridMenuItems = new HashMap<>();
        gridMenuItems.put("Clear", this.clearAction);
        gridMenuItems.put("Load", this.loadAction);
        gridMenuItems.put("Store", this.storeAction);
        gridMenuItems.put("Exit", this.exitAction);

        HashMap<String, Action> goMenuItems = new HashMap<>();
        goMenuItems.put("Halt", this.haltAction);
        goMenuItems.put("Tick (Single Step)", this.tickAction);
        goMenuItems.put("Agonizing", this.agonizingAction);
        goMenuItems.put("Slow", this.slowAction);
        goMenuItems.put("Medium", this.mediumAction);
        goMenuItems.put("Fast", this.fastAction);

        for (String menuItem : gridMenuItems.keySet()) {
            //when
            menuController.action("Grid", menuItem);
            //then
            verify(gridMenuItems.get(menuItem)).action();
        }

        for (String menuItem : goMenuItems.keySet()) {
            //when
            menuController.action("Go", menuItem);
            //then
            verify(goMenuItems.get(menuItem)).action();
        }
    }
}