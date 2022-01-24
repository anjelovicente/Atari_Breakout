/*
 * File: Breakout.java
 * ---------------------
 * This class is a starter code for LBYCPEI Breakout exercise
 *
 */

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import acm.util.*;
import stanford.cs106.audio.StdAudio;

import java.applet.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class extra extends GraphicsProgram {

    // Breakout CONSTANTS
    public static final int APPLICATION_WIDTH = 620;
    public static final int APPLICATION_HEIGHT = 720;

    private static final int NBRICKS_PER_ROW = 11;
    private static final int NBRICKS_PER_COL = 11;
    private static final double BRICK_GAP = 4;
    private static final double BRICK_WIDTH = Math.floor(
            (APPLICATION_WIDTH - (NBRICKS_PER_ROW + 1.0) * BRICK_GAP) / NBRICKS_PER_ROW);
    private static final double BRICK_HEIGHT = 12;
    private static final double BRICK_Y_OFFSET = 60;
    private static final double BRICK_Y_OFFSET1 = 65;

    private static final double PADDLE_WIDTH = 80;
    private static final double PADDLE_HEIGHT = 12;
    private static final double PADDLE_Y_OFFSET = 30;

    private static final double BALL_RADIUS = 12;
    private static final double VELOCITY_Y = 8.0;
    private static final double VELOCITY_X_MIN = 2.0;
    private static final double VELOCITY_X_MAX = 4.0;
    private static final int BALL_DELAY = 1000 / 60;

    private double dx;
    private double dy;
    private RandomGenerator rgen = RandomGenerator.getInstance();

    private GRect paddle;
    private GOval ball;


    public void run() {

        int l=3;

        setTitle("LBYCPEI Breakout");
        drawWalls(getWidth() / 2, BRICK_Y_OFFSET);
        numberOfTries(3);

        while (true) {
            drawGameUtil();
            startGame();
            l= l-1;
            gameConditions(l);
            remove(paddle);
        }
    }

    public static void main(String[] args) {
        new Breakout().start(args);
    }

    private void drawPaddle() {
        paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle, 0.5 * (getWidth() - PADDLE_WIDTH), getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        addMouseListeners();
    }

    private void drawBall() {
        ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
        ball.setFilled(true);
        ball.setFillColor(Color.BLUE);
        add(ball, 0.5 * getWidth() - BALL_RADIUS, 0.5 * getHeight() - BALL_RADIUS);
    }

    private void drawWalls(double cx, double cy) {

        for (int row = 0; row < NBRICKS_PER_ROW; row++) {
            for (int column = 0; column < NBRICKS_PER_COL; column++) {

                double x = cx - (NBRICKS_PER_ROW * BRICK_WIDTH) / 2 - ((NBRICKS_PER_ROW - 1) * BRICK_GAP) / 2 + column * BRICK_WIDTH + column * BRICK_GAP;
                double y = cy + row * BRICK_HEIGHT + row * BRICK_GAP;

                if (row == 1) {
                    GImage brick1 = new GImage("PNG/02-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/01-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
                if (row == 2) {
                    GImage brick1 = new GImage("PNG/04-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/03-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
                if (row == 3) {
                    GImage brick1 = new GImage("PNG/06-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/05-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
                if (row == 4) {
                    GImage brick1 = new GImage("PNG/08-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/07-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
                if (row == 5) {
                    GImage brick1 = new GImage("PNG/10-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/09-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
                if (row == 6) {
                    GImage brick1 = new GImage("PNG/12-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/11-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
                if (row == 7) {
                    GImage brick1 = new GImage("PNG/14-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/13-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
                if (row == 8) {
                    GImage brick1 = new GImage("PNG/16-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/15-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
                if (row == 9) {
                    GImage brick1 = new GImage("PNG/18-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/17-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
                if (row == 10) {
                    GImage brick1 = new GImage("PNG/20-Breakout-Tiles.png", x, y);
                    brick1.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick1);
                    GImage brick2 = new GImage("PNG/19-Breakout-Tiles.png", x, y);
                    brick2.setSize(BRICK_WIDTH, BRICK_HEIGHT);
                    add(brick2);
                }
            }
        }
        return;
    }

    private int SCORE=0;

    private void score(int s){
        if (s==0){
            GRect block = new GRect(100, 30);
            block.setColor(Color.WHITE);
            block.setFilled(true);
            add(block, 50, 20);

            GLabel b_number = new GLabel("SCORE: "+ SCORE, 50 , 35);
            b_number.setFont("serif-bold-15");
            b_number.setColor(Color.BLUE);
            add(b_number);
        }
    }

    public void mouseMoved(MouseEvent mouse) {
        int mouse_x = mouse.getX();

        if (mouse_x > (0.5 * -PADDLE_WIDTH) && (mouse_x < (getWidth() - 0.5 * PADDLE_WIDTH))) {
            paddle.setLocation(mouse_x - 0.5 * PADDLE_WIDTH, paddle.getY());
        }
    }

    private void getBallVelocity() {
        dy = VELOCITY_Y;
        dx = rgen.nextDouble(VELOCITY_X_MIN, VELOCITY_X_MAX);
        if (rgen.nextBoolean(0.5)) {
            dx = -dx;
        }
    }

    private void moveBall() {
        score(0);
        ball.move(dx, dy);
        //walls
        if ((ball.getX() - dx <= 0 && dx < 0 )|| (ball.getX() + dx >= (getWidth() - BALL_RADIUS*2) && dx>0)) {
            dx = -dx;
        }
        //top wall
        if ((ball.getY() - dy <= BRICK_Y_OFFSET1 && dy < 0 )) {
            dy = -dy;
        }

        //objects
        GObject collider = getCollidingObject();
        if (collider == paddle) {
            if(ball.getY() >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS*2 && ball.getY() < getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS*2 + 4) {
                dy = -dy;
            }
        }

        else if (collider != null) {
            StdAudio.play("bounce.wav");
            SCORE=SCORE+10;
            remove(collider);
            dy = -dy;
        }
        pause (BALL_DELAY);
    }

    private GObject getCollidingObject() {

        if((getElementAt(ball.getX(), ball.getY())) != null) {
            return getElementAt(ball.getX(), ball.getY());
        }
        else if (getElementAt( (ball.getX() + BALL_RADIUS*2), ball.getY()) != null ){
            return getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY());
        }
        else if(getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS*2)) != null ){
            return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS*2);
        }
        else if(getElementAt((ball.getX() + BALL_RADIUS*2), (ball.getY() + BALL_RADIUS*2)) != null ){
            return getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY() + BALL_RADIUS*2);
        }
        //no objects present
        else{
            return null;
        }

    }

    private void startGame() {
        waitForClick();
        getBallVelocity();
        while (true) {
            moveBall();
            if (ball.getY() >= getHeight()) {
                break;
            }
        }
    }

    private void drawGameUtil(){
        drawPaddle();
        drawBall();
    }

    private void gameConditions(int l){
        if (SCORE == 220*10){
            gameWinner();
            exit();
        }
        else if (l==2){
            numberOfTries(2);
        }
        else if (l==1) {
            numberOfTries(1);
        }
        else if (l==0) {
            numberOfTries(0);
            gameOver();
            exit();
        }
    }

    private void numberOfTries(int x) {
        GLabel text = new GLabel("Balls Used:", 470, 35);
        text.setFont("serif-bold-15");
        add(text);

        GImage life = new GImage("PNG/58-Breakout-Tiles.png", 550, 30);
        life.setSize(20, 20);
        add(life);

        GImage life2 = new GImage("PNG/58-Breakout-Tiles.png", 570, 30);
        life2.setSize(20, 20);
        add(life2);

        GImage life3 = new GImage("PNG/58-Breakout-Tiles.png", 590, 30);
        life3.setSize(20, 20);
        add(life3);

        if (x == 2) {
            GLine x1 = new GLine(10, 10,30, 30);
            x1.setColor(Color.RED);
            add(x1, 550, 30);
            GLine x11 = new GLine(30, 10,10, 30);
            x11.setColor(Color.RED);
            add(x11, 570, 30);
        }
        else if (x == 1) {
            GLine x1 = new GLine(10, 10,30, 30);
            x1.setColor(Color.RED);
            add(x1, 550, 30);
            GLine x11 = new GLine(30, 10,10, 30);
            x11.setColor(Color.RED);
            add(x11, 570, 30);
            GLine x2 = new GLine(10, 10,30, 30);
            x2.setColor(Color.RED);
            add(x2, 570, 30);
            GLine x21 = new GLine(30, 10,10, 30);
            x21.setColor(Color.RED);
            add(x21, 590, 30);
        }
        else if (x == 0) {
            GLine x1 = new GLine(10, 10,30, 30);
            x1.setColor(Color.RED);
            add(x1, 550, 30);
            GLine x11 = new GLine(30, 10,10, 30);
            x11.setColor(Color.RED);
            add(x11, 570, 30);
            GLine x2 = new GLine(10, 10,30, 30);
            x2.setColor(Color.RED);
            add(x2, 570, 30);
            GLine x21 = new GLine(30, 10,10, 30);
            x21.setColor(Color.RED);
            add(x21, 590, 30);
            GLine x3 = new GLine(10, 10,30, 30);
            x3.setColor(Color.RED);
            add(x3, 590, 30);
            GLine x31 = new GLine(30, 10,10, 30);
            x31.setColor(Color.RED);
            add(x31, 610, 30);
        }

    }

    private void gameOver() {
        removeAll();

        GLabel gameOver = new GLabel ("Game Over", getWidth()/2 - 9, getHeight()/2);
        gameOver.move(-gameOver.getWidth()/2, -gameOver.getHeight());
        gameOver.setFont("SERIF-bold-15");
        gameOver.setColor(Color.RED);
        add(gameOver);
        GLabel b_number = new GLabel("SCORE: "+ SCORE, getWidth()/2 , getHeight()/2 + 20);
        b_number.move(-gameOver.getWidth()/2, -gameOver.getHeight());
        b_number.setColor(Color.RED);
        b_number.setFont("SERIF-bold-15");
        add(b_number);
        waitForClick();
    }

    private void gameWinner() {
        GLabel gameWinner = new GLabel ("Winner!!", getWidth()/2, getHeight()/2);
        gameWinner.move(-gameWinner.getWidth()/2, -gameWinner.getHeight());
        gameWinner.setColor(Color.GREEN);
        add(gameWinner);
        GLabel b_number = new GLabel("SCORE: "+ SCORE, getWidth()/2 , getHeight()/2 + 20);
        b_number.move(-gameWinner.getWidth()/2, -gameWinner.getHeight());
        b_number.setColor(Color.GREEN);
        b_number.setFont("SERIF-bold-15");
        add(b_number);
        waitForClick();
    }

}
