package com.LifeGame.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;

@Component
public class Service {

    public File userSelected(final String startHere,
                                    final String extension,
                                    final String description,
                                    final String selectButtonText)
            throws FileNotFoundException {
        FileFilter filter =
                new FileFilter() {
                    public boolean accept(File f) {
                        return f.isDirectory()
                                || (extension != null
                                && f.getName().endsWith(extension));
                    }

                    public String getDescription() {
                        return description;
                    }
                };

        JFileChooser chooser = new JFileChooser(startHere);
        chooser.setFileFilter(filter);

        int result = chooser.showDialog(null, selectButtonText);
        if (result == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile();

        throw new FileNotFoundException("No file selected by user");
    }
    public MapData load() throws InvalidFileLoadedException {
        ObjectMapper loadMapper = new ObjectMapper();

        try {
            // JSON 타입의 파일 -> 객체로 변환
            MapData mapData = loadMapper.readValue(new FileInputStream(this.userSelected(".",".json","JSON File","Load")), MapData.class);
            return mapData;

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new InvalidFileLoadedException();
    }

    public void store(MapData mapData) {

        ObjectMapper storeMapper = new ObjectMapper();

        try {
            FileOutputStream out = new FileOutputStream(
                    this.userSelected(".", ".json", "JSON File", "Write"));

            // 객체 - > JSON 타입의 파일로 변환
            storeMapper.writeValue(out, mapData);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
