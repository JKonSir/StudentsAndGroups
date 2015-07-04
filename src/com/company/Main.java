package com.company;

import DAO.DataBaseInit;
import gui.PrimaryFrame;

import javax.swing.*;

public class Main implements Runnable {

    public static void main(String[] args) {
        DataBaseInit.setUserName("testUser");
        DataBaseInit.setPassword("qwerty");
//        DataBaseInit.setConnectionURL("jdbc:derby://localhost:1527/testDB");
        DataBaseInit.setConnectionURL("jdbc:postgresql://localhost:5432/testDB1");

//        DataBaseInit.init();

        new Thread(new Main()).start();
    }

    public void run() {
        try {
            PrimaryFrame primaryFrame = new PrimaryFrame();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
