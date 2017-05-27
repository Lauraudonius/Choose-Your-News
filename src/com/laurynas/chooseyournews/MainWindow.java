package com.laurynas.chooseyournews;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Laurynas on 2017-05-27.
 */
public class MainWindow {
    private JButton button1;
    private JPanel panelMain;
    private JList list1;

    public MainWindow(){
        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");
        list1.setModel(listModel);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                button1.setText(String.valueOf(index));
            }
        });
    }
    public static void main(String[] args){
        JFrame jFrame = new JFrame("App");
        jFrame.setContentPane(new MainWindow().panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setSize(new Dimension(640, 640));
        jFrame.setVisible(true);
        //jFrame.setLocationRelativeTo(null);﻿
    }
}
