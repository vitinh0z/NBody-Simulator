package Model;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private List<Body> bodies;

    public Simulation() {
        this.bodies = new ArrayList<>();
    }

    public void addBody(Body body) {
        this.bodies.add(body);
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void update (double dt) {
        // Limpar aceleração anterior
        for (Body body : bodies) {
            body.resetAcceleration();
        }

        // Calcular Forças
        for (int i = 0; i < bodies.size(); i++){
            Body bodyA = bodies.get(i);

            for (int j = 0; j < bodies.size(); j++){

                Body bodyB = bodies.get(j);

                if (bodyA == bodyB) continue;

                Vector forceExercisedByBodyB = bodyA.calculateForceFrom(bodyB);
                bodyA.applyForce(forceExercisedByBodyB);
            }
        }

        // Mover Corpos
        for (Body body : bodies){
            body.update(dt);
        }
    }
}