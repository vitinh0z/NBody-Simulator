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
    public void start(Stage stage)  {

        simulation = new Simulation();
        criarCenarioInicial();


        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext pincel = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        stage.setTitle("Simulação Gravitacional");
        stage.setScene(scene);
        stage.show();

        new AnimationTimer(){


            @Override
            public void handle(long l) {

                simulation.update(0.01);

                pincel.setFill(Color.BLACK);
                pincel.fillRect(0,0, WIDTH, HEIGHT);

                desenharCorpos(pincel);

            }
        }.start();


    }

    public void criarCenarioInicial(){

        Body sol = new Body(
                100000000,
                30,
                new Vector(0,0),
                new Vector(0,0)
        );

        Body terra = new Body(
                1000,
                10,
                new Vector(300,0),
                new Vector(0, 12.9)
        );

        simulation.addBody(sol);
        simulation.addBody(terra);

    }


    private void desenharCorpos(GraphicsContext pincel) {
        double centroX = WIDTH / 2.0;
        double centroY = HEIGHT / 2.0;

        for (Body body : simulation.getBodies()) {

            double xFisico = body.getPosition().getX();
            double yFisico = body.getPosition().getY();


            double xTela = centroX + xFisico;
            double yTela = centroY + yFisico;


            double raio = body.getRadius();
            pincel.setFill(Color.WHITE); // Cor do planeta


            pincel.fillOval(xTela - raio, yTela - raio, raio * 2, raio * 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}