package com.example.uloha_2d_grafika;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


public class MainApplication extends Application {
    public static final int SCREEN_WIDTH = 1900;
    public static final int SCREEN_HEIGHT = 920;

    GraphicsContext graphicsContext;

    public int speed = 10;

    Player player;

    Random random = new Random();

    double mouseX;
    double mouseY;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.show();

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
            mouseX = event.getX();
            mouseY = event.getY();
            
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > 100000000l/speed) {
                    lastTick = now;
                    tick();
                }
            }
        };
        animationTimer.start();

        player = new Player(random.nextInt(SCREEN_WIDTH - 100), random.nextInt(SCREEN_HEIGHT - 100));

    }

    int pocitadlo = 0;
    int imgpocitadlo = 0;

    private void tick(){

        if(!player.zastavit) {
            if (imgpocitadlo == 19) {
                player.setTick(imgpocitadlo);
                player.loadTextures();
                imgpocitadlo = 0;
            } else {
                player.setTick(imgpocitadlo);
                player.loadTextures();
            }
        }
        else{
            if(imgpocitadlo == 10){
                imgpocitadlo = 0;
                player.zastavit = false;
                player.changeDirection();
                player.x = random.nextInt(SCREEN_WIDTH - player.width);
                player.y = random.nextInt(SCREEN_HEIGHT - player.height);
                player.setTick(imgpocitadlo);
                player.loadTextures();
            }
            else{
                player.setTick(imgpocitadlo);
                player.loadTextures();
            }
        }

        if(pocitadlo == 30) {
            player.direction = player.changeDirection();
            pocitadlo = 0;
        }
        clearScreen();


        graphicsContext.drawImage(player.image, player.x, player.y, player.width, player.height);

        switch (player.direction){
            case 0 -> player.downMovement();
            case 1 -> player.upMovement();
            case 2 -> player.leftMovement();
            case 3 -> player.rightMovement();
            default -> {

            }
        }

        if(player.x + player.width >= SCREEN_WIDTH){
            player.direction = 2;
        }
        else if(player.x <= 0){
            player.direction = 3;
        }
        else if(player.y <= 0){
            player.direction = 0;
        }
        else if(player.y + player.height >= SCREEN_HEIGHT){
            player.direction = 1;
        }
        else if(!player.zastavit){
            pocitadlo++;
        }


        if(mouseX >= player.x && mouseX <= player.x + player.width && mouseY >= player.y && mouseY <= player.y + player.height){
            player.direction = 4;
            imgpocitadlo = 0;
            player.zastavit = true;
            mouseX = 0;
            mouseY = 0;
        }
        imgpocitadlo++;
        graphicsContext.setFill(Color.GREENYELLOW);
    }

    private void clearScreen() {
        graphicsContext.clearRect(0,0, SCREEN_WIDTH, SCREEN_WIDTH);
    }

    public static void main(String[] args) {
        launch();
    }
}