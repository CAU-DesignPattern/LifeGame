package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HaltActionTest {

    @Mock
    private MenuController menuController;
    @InjectMocks
    private HaltAction haltAction;

    @BeforeEach
    void setUp() {
    }
}