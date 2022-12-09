package com.LifeGame.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

  private static String TEST_FILE_NAME = "test.json";
  @Spy
  private Service service;

  @BeforeEach
  void setup() throws IOException {
    File testFile = new File(TEST_FILE_NAME);
    if (testFile.exists()) {
      if (!testFile.delete()) {
        throw new RuntimeException("cannot delete existed test file");
      }
    }

    File newTestFile = new File(TEST_FILE_NAME);
    if (!newTestFile.createNewFile()) {
      throw new RuntimeException("cannot create new test file");
    }
    Mockito.doReturn(newTestFile).when(service).userSelected(any(), any(), any(), any());
  }
  @AfterAll
  static void cleanUp(){
    File testFile = new File(TEST_FILE_NAME);
    if (testFile.exists()) {
      if (!testFile.delete()) {
        throw new RuntimeException("cannot delete existed test file");
      }
    }
  }


  @Test
  @DisplayName("Load 기능 테스트")
  void loadTest() throws IOException {
    // when
    MapData randomMapData = createRandomMapData();
    File testFile = new File(TEST_FILE_NAME);
    Files.writeString(testFile.toPath(), mapToString(randomMapData));
    MapData loadedData = service.load();

    // then
    Assertions.assertEquals(
        mapToString(randomMapData),
        mapToString(loadedData),
        "loaded map must equal generated map");
  }

  @Test
  @DisplayName("Store 기능 테스트")
  void storeTest() throws IOException {
    // when
    MapData randomMapData = createRandomMapData();
    service.store(randomMapData);

    // then
    File testFile = new File(TEST_FILE_NAME);
    assertTrue(testFile.exists(), "test file must be generated");
    List<String> lines = Files.readAllLines(testFile.toPath());
    Assertions.assertEquals(lines.size(), 1, "file must contain 1 line string");
    Assertions.assertEquals(lines.get(0), mapToString(randomMapData),
        "saved map must equal generated map");
  }

  private MapData createRandomMapData() {
    int size = new Random().nextInt(20);
    int[][] map = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        map[i][j] = new Random().nextInt();
      }
    }
    return new MapData(size, map);
  }

  private String mapToString(MapData mapData) {
    ObjectMapper storeMapper = new ObjectMapper();
    try {
      return storeMapper.writeValueAsString(mapData);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
