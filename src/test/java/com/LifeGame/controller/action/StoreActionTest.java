package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import com.LifeGame.model.Model;
import com.LifeGame.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

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
    @DisplayName("[constructor] menuController의 addMenuItem 메서드를 호출하고 파라미터가 잘 넘어가는지 테스트")
    void constructorTest() {

        //given
        String menu = "Grid";
        String menuItem = "Store";

        //when
        //then
        verify(this.menuController).addMenuItem(menu, menuItem, this.storeAction);
    }
}