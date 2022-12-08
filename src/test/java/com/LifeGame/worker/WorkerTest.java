package com.LifeGame.worker;

import com.LifeGame.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WorkerTest {

    @Mock
    private Model model;
    @InjectMocks
    private Worker worker;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[startThread/alive] 생성되었던 thread를 중단시키고 새로운 thread가 잘 시작되는지 테스트")
    void startAlivedThreadTest() throws NoSuchFieldException, IllegalAccessException {

        //given
        Field field = this.worker.getClass().getDeclaredField("thread");
        field.setAccessible(true);

        this.worker.setSpeed(100);
        this.worker.startThread();

        Thread prev = (Thread) field.get(this.worker);

        //when
        this.worker.startThread();

        //then
        assertTrue(((Thread) field.get(this.worker)).isAlive());
        assertNotEquals(prev, field.get(this.worker));
    }

    @Test
    @DisplayName("[startThread/not alive] 생성된 thread가 없을 때 새로운 thread가 잘 시작되는지 테스트")
    void startThreadTest() throws NoSuchFieldException, IllegalAccessException {

        //given
        Field field = this.worker.getClass().getDeclaredField("thread");
        field.setAccessible(true);

        this.worker.setSpeed(100);

        //when
        this.worker.startThread();

        //then
        assertTrue(((Thread) field.get(this.worker)).isAlive());
    }

    @Test
    @DisplayName("[stopThread/alive] 생성된 thread가 있을 때 thread가 잘 중단되는지 테스트")
    void stopAlivedThreadTest() throws NoSuchFieldException, IllegalAccessException {

        //given
        Field field = this.worker.getClass().getDeclaredField("thread");
        field.setAccessible(true);

        this.worker.setSpeed(100);
        this.worker.startThread();

        Thread prev = (Thread) field.get(this.worker);

        //when
        this.worker.stopThread();

        //then
        assertTrue(((Thread) field.get(this.worker)).isInterrupted());
        assertEquals(prev, field.get(this.worker));
    }

    @Test
    @DisplayName("[stopThread/not alive] 생성된 thread가 없을 때 잘 무시하는지 테스트")
    void stopThreadTest() throws NoSuchFieldException, IllegalAccessException {

        //given
        Field field = this.worker.getClass().getDeclaredField("thread");
        field.setAccessible(true);

        this.worker.setSpeed(100);

        //when
        this.worker.stopThread();

        //then
        assertNull(field.get(this.worker));
    }
}