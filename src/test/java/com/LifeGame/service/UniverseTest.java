package com.LifeGame.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UniverseTest {

    Universe universe = new Universe();
    String outputPath = "/Users/jisoo/Desktop/store test";
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() throws FileNotFoundException {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    //현재 state가 설정한 파일 형식으로 지정한 위치에 잘 저장되는지 테스트
    @DisplayName("store 테스트")
    @Test
    public void storeTest() throws Exception {
        // given
        String fileName = "mm";
        FileOutputStream out = new FileOutputStream("/Users/jisoo/Desktop/" + fileName);
        // when
        universe.doStore();
        // then
        assertEquals("성공", outputStreamCaptor.toString().trim());
        out.close();
    }

    // 저장에 대한 실패 테스트(아무 작업 없이 write 누른 경우 에러 다이얼로그)
    @DisplayName("store fail 테스트")
    @Test
    public void storeFailTest() throws Exception {
        // given
        String fileName = "mm";
        FileOutputStream out = new FileOutputStream("/Users/jisoo/Desktop/" + fileName);
        // when
        universe.doStore();
        // then
        assertEquals("실패", outputStreamCaptor.toString().trim());
        out.close();
    }

}