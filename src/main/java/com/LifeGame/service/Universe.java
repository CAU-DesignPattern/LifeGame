package com.LifeGame.service;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.time.Clock;
import java.util.Collection;

// caretaker
public class Universe {

    Originator originator; // 얘를 메멘토 만들어서 파일에 저장 불러오기
    public static File userSelected(final String startHere,
                                    final String extension,
                                    final String description,
                                    final String selectButtonText)
            throws FileNotFoundException {
        javax.swing.filechooser.FileFilter filter =
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

//    public void load(InputStream in) throws IOException {
//        ObjectInputStream source = new ObjectInputStream(in);
//        liveCells = (Collection) (source.readObject());
//    }
//
//    public void flush(OutputStream out) throws IOException {
//        ObjectOutputStream sink = new ObjectOutputStream(out);
//        sink.writeObject(liveCells);
//    }

    void doLoad() {
        try {
            FileInputStream in = new FileInputStream(
                    Universe.userSelected(".", ".life", "Life File", "Load"));

            //Storable memento = originator.createMemento();
            //memento.load(in);
            System.out.println("성공");
            // TODO : memento 로드 후 처리해 줘야함

            in.close();
        } catch (IOException theException) {
            JOptionPane.showMessageDialog(null, "Read Failed!",
                    "The Game of Life", JOptionPane.ERROR_MESSAGE);
        }

    }
    void doStore() {
        try {
            FileOutputStream out = new FileOutputStream(
                    Universe.userSelected(".", ".life", "Life File", "Write"));

            System.out.println("성공");
            //Storable memento = originator.createMemento();
            //memento.flush(out);
            out.close();
        } catch (IOException theException) {
            System.out.println("실패");
            JOptionPane.showMessageDialog(null, "Write Failed!",
                    "The Game of Life", JOptionPane.ERROR_MESSAGE);
        }
    }
}
