package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import com.LifeGame.model.Model;
import com.LifeGame.service.InvalidFileLoadedException;
import com.LifeGame.service.MapData;
import com.LifeGame.service.Service;
import com.LifeGame.view.LifePanel;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadActionTest {

  @Mock
  private Service service;
  @Mock
  private LifePanel lifePanel;
  @Mock
  private Model model;
  @Mock
  private MenuController menuController;
  @InjectMocks
  private LoadAction loadAction;

  @BeforeEach
  void setUp() {
  }

  @Test
  @DisplayName("[action/try] action 기능 테스트")
  void actionTryTest() {
    //given
    int mapSize = 0;
    int[][] liveCells = {{0, 0}};
    MapData mapData = new MapData(mapSize, liveCells);
    when(this.service.load()).thenReturn(mapData);

    //when
    this.loadAction.action();

    //then
    verify(this.model).clearMap();
    verify(this.model).setMap(liveCells);
  }

  @Test
  @DisplayName("[action/catch] action 기능 테스트")
  void actionCatchTest() throws AWTException {
    //given
    MockedStatic<JOptionPane> ignoreOption = Mockito.mockStatic(JOptionPane.class);
    when(this.service.load()).thenThrow(new InvalidFileLoadedException());

    //when
    this.loadAction.action();
    //then
    verify(this.model).clearMap();

    //cleanUp
    ignoreOption.close();
  }
}