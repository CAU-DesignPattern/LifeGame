package com.LifeGame.controller;

import com.LifeGame.view.LifePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LifeControllerTest {

    @Mock
    private LifePanel lifePanel;
    @InjectMocks
    private LifeController lifeController;

    @BeforeEach
    void setUp() {
    }
}