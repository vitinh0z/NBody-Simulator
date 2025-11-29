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

import java.util.Random;

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

                simulation.update(0.1);

                pincel.setFill(Color.BLACK);
                pincel.fillRect(0,0, WIDTH, HEIGHT);

                desenharCorpos(pincel);

            }
        }.start();


    }

    public void criarCenarioInicial(){



        Body buracoNegro = new Body (
                5000,
                6.0,
                new Vector(0, 0),
                new Vector(0, 0)
        );

        simulation.addBody(buracoNegro);

        Random random = new Random();

        long numeroDeParticulas = 1500L;

        for (int i = 0; i<numeroDeParticulas; i++){

        double x = random.nextGaussian() * 120;
        double y = random.nextGaussian() * 120;

        double mass = 0.5 + random.nextDouble() * 2.0;

        double raio = 0.9;

        Vector posicao = new Vector(x, y);

        Vector velocidade = new Vector(random.nextGaussian() * 0.5, random.nextGaussian() * 0.5);

        Body body = new Body(mass, raio, posicao, velocidade);

        simulation.addBody(body);

        }


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

            if (body.getMass() <= 1.5){
                pincel.setFill(Color.rgb(13, 10, 136));
                pincel.fillOval(xTela - raio, yTela - raio, raio*2, raio*2);
                continue;

            }

            if (body.getMass() > 1000 ){
                pincel.setFill(Color.BLACK);
                pincel.fillOval(xTela - raio, yTela - raio, raio*2, raio*2);


                pincel.setStroke(Color.ORANGE);
                pincel.setLineWidth(2);
                pincel.strokeOval(xTela - raio*2.5, yTela - raio*2.5, raio*5, raio*5);
                continue;
            }

            pincel.setFill(Color.DARKVIOLET);
            pincel.fillOval(xTela - raio, yTela - raio, raio * 2, raio * 2);
        }

        pincel.setGlobalAlpha(1.0);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
