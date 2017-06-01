package com.laurynas.chooseyournews;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by Laurynas on 2017-05-27.
 */
public class MainWindow {
    private JButton button1;
    private JPanel panelMain;
    private JScrollPane scrollPane;
    private JList list1  = new JList();

    public MainWindow(){
        Headline[] data = {};
        try {
            data = Main.main();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Headline[] headlines = data;
        String[] subjects = new String[headlines.length];
        for(int i = 0;i < headlines.length;i++){
            subjects[i] = headlines[i].getTema();
        }
        String[] links = new String[headlines.length];
        for(int i = 0;i < headlines.length;i++){
            links[i] = headlines[i].getLink();
        }
        String[] headers = new String[headlines.length];
        for(int i = 0;i < headlines.length;i++){
            headers[i] = headlines[i].getHeadline().replace("<em>", "").replace("</em>", "");
        }
        list1 = new JList(headers);
        System.out.println(headers[0]);
        //scrollPane = new JScrollPane(list1);
        scrollPane.setVisible(true);
        scrollPane.setViewportView(list1);
        //panelMain.add(scrollPane);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                button1.setText(links[index]);
            }
        });
    }
    public static void main(String[] args){
        JFrame jFrame = new JFrame("App");
        jFrame.setContentPane(new MainWindow().panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setSize(new Dimension(1000, 640));
        jFrame.setVisible(true);
        //jFrame.setLocationRelativeTo(null);﻿
    }
}
