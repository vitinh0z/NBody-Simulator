package Model;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private List<Body> bodies;

    public Simulation(List<Body> bodies) {
        this.bodies = bodies = new ArrayList<>();
    }

    public void update (double dt) {
        for (Body body : bodies) {

            body.resetAcceleration();
        }

        for (int i = 0; i<bodies.size(); i++){
            Body bodyA = bodies.get(i);

            for (int j = 0; j<bodies.size(); j++){
                Body bodyB = bodies.get(i);

                if (bodyA == bodyB) continue; // mesmo objeto nao pode atrair a si mesmo

                Vector forceExercisedByBodyB = bodyA.calculateForceFrom(bodyB);

                bodyA.applyForce(forceExercisedByBodyB);
            }

        }

        for (Body body : bodies){
            body.update(dt);
        }


    }
}
