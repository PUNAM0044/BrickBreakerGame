/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.brickbreakergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author punmk
 */
public class gameplay extends JPanel implements KeyListener,ActionListener {
    private boolean play=false;
    private int score=0;
    private int totalBricks=21;
    private Timer timer;
    private int delay=8;
    private int playerX=310;
    private int ballPosX=120;
    private int ballPosY=350;
    private int ballXDir=-1;
    private int ballYDir=-2;
    private mapgenerator map;
    
    public gameplay(){
        map=new mapgenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
        
    }
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score, 590, 30);
        
        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 8);
        
        g.setColor(Color.GREEN);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        
        if(ballPosY>570){
            play=false;
            ballXDir=0;
            ballYDir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game over score : "+score, 190, 300);
            
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart "+score, 190, 340);
        }
        if(totalBricks==0){
            play=false;
            ballYDir=-2;
            ballXDir=-1;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game over score : "+score, 190, 300);
            
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart "+score, 190, 340);
        }
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        timer.start();
        if(play){
            if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYDir=-ballYDir;
            }
            A:
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX=j*map.brickWidth+80;
                        int brickY=j*map.brickHeight+50;
                        int bricksWidth=map.brickWidth;
                        int bricksHeight=map.brickHeight;
                        
                        Rectangle rect=new Rectangle(brickX,brickY,bricksWidth,bricksHeight);
                        Rectangle ballrect=new Rectangle(ballPosX,ballPosY,20,20);
                        Rectangle brickrect=rect;
                        if(ballrect.intersects(brickrect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score+=5;
                            if(ballPosX+19<=brickrect.x||ballPosX+1>=brickrect.x+bricksWidth){
                                ballXDir=-ballXDir;
                            }else{
                                ballYDir=-ballYDir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosX+=ballXDir;
            ballPosY+=ballYDir;
            if(ballPosX<0){
                ballXDir=-ballXDir;
            }
            if(ballPosY<0){
                ballYDir=-ballYDir;
            }
        }
        repaint();
    }
     @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX>=600){
                playerX=600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
           if(playerX<10){
                playerX=10;
            }else{
                moveLeft();
            }  
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                ballPosX=120;
                ballPosY=350;
                ballXDir=-1;
                ballYDir=-2;
                score=0;
                playerX=310;
                totalBricks=21;
                map=new mapgenerator(3,7);
                repaint();
            }
        }
    }
    
    public void moveRight(){
        play=true;
        playerX+=20;
    }
    
     public void moveLeft(){
        play=true;
        playerX-=20;
    }
     
     @Override
     public void keyTyped(KeyEvent e){
        // throw new UnsupportedOperationException("Not supporetd yet.");
     }
     @Override
     public void keyReleased(KeyEvent e){
       //  throw new UnsupportedOperationException("Not supporetd yet.");
     }
    
}
