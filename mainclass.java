/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brickbreakergame;
import javax.swing.JFrame;
/**
 *
 * @author punmk
 */
public class mainclass {
    public static void main(String[] args){
        JFrame obj=new JFrame();
        gameplay gamePlay=new gameplay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Brick Breaker Game");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
        
    }
}
