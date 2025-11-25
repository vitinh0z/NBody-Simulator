package view;

import Model.Body;
import Model.Simulation;
import Model.Vector;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {


    private final int WIDTH = 800;
    private final int HEIGHT = 600;


    Simulation simulation;

    @Override
    public void start(Stage stage) throws Exception {

        simulation = new Simulation();
        criarCenarioInicial();


        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext pincel = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        primaryStage.setTitle("Simulação Gravitacional");
        primaryStage.setScene(scene);
        primaryStage.show();

        new AnimationTimer(){


            @Override
            public void handle(long l) {

                simulation.update(0.1);

                pincel.setFill(Color.BLACK);
                pincel.fillRect(0,0, WIDTH, HEIGHT);

                desenharCorpos(pincel);

            }
        }.start();


    }

    public void criarCenarioInicial(){

        Body sol = new Body(
                1_000_000,
                30,
                new Vector(0,0),
                new Vector(0,0)
        );

        Body terra = new Body(
                100,
                10,
                new Vector(300,0),
                new Vector(0, 15)
        );

        simulation.addBody(sol);
        simulation.addBody(terra);

    }



}