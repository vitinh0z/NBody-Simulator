package Model;

public class Body {

    private double mass;

    private double radius;

    private Vector position;

    private Vector velocity;

    private Vector aceleration;

    public static final double G = 6.67430e-11;

    public Body(double mass, double radius, Vector position, Vector velocity) {
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.velocity = velocity;
        this.aceleration = new Vector(0, 0); // Começa zerada
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getAceleration() {
        return aceleration;
    }

    public void resetAcceleration() {
        this.aceleration = new Vector(0, 0);
        // Cria um vetor (0,0) novo e limpo.
    }


    // Calcula a força gravitacional que "other" exerce sobre "this".
    // Retorna um vetor força (Fx, Fy) conforme a Lei da Gravitação Universal.
    public Vector calculateForceFrom(Body other){

        // ------------------------
        // 1) Vetor entre os corpos
        // ------------------------
        // delta = posiçãoDoOutro - minhaPosição
        // Este vetor aponta DA minha posição → ATÉ a posição do outro corpo.
        // Ele representa a “linha imaginária” que conecta os dois objetos.

        //           this (corpo A)                    other (corpo B)
        //               ● -----------------------------> ●
        //            vetor delta (posição B - posição A)

        Vector delta = other.getPosition().subs(this.getPosition());

        // ------------------------
        // 2) Distância entre os corpos (r)
        // ------------------------
        // r = magnitude de delta
        // A magnitude do vetor é a distância real entre os corpos no espaço.
        // Usada tanto para a direção quanto para o cálculo da força.
        double r = delta.magnitude();

        double softening = 1e-2; // Ou um valor pequeno ajustável
        double distanceSq = (r * r) + (softening * softening);

        // -------------------------------------
        // 3) Direção da força (vetor unitário)
        // -------------------------------------
        // Normalizamos delta → transformamos num vetor unitário (magnitude 1).
        // Isso preserva a direção exata, mas remove o tamanho.
        // A força final virá da magnitude * multiplicada* por esse vetor.
        Vector direction = delta.normalizacao();

        // ---------------------------------------------
        // 4) Magnitude da força gravitacional (escalar)
        // ---------------------------------------------
        // Fórmula da força gravitacional de Newton:
        //
        //      F = G * m1 * m2 / r²
        //
        // Aqui calculamos APENAS o valor numérico da força,
        // sem direção ainda. Este é um número puro (um escalar).
        double magnitude = (G * this.getMass() * other.getMass()) / distanceSq;

        // ---------------------------------------------------
        // 5) Força final: direção × magnitude (vira um vetor)
        // ---------------------------------------------------
        // transformamos a força escalar em vetor.
        // direction diz PARA ONDE a força aponta.
        // magnitude diz QUANTO de força existe.
        //
        // O produto dos dois gera o vetor força completo,
        // que pode ser usado para cálculo de aceleração:
        //          a = F / m
        return direction.multiEscalar(magnitude);
    }
    public void applyForce (Vector force){

        Vector acelerationFromThisForce = force.multiEscalar(1.0/this.mass);

        this.aceleration = this.aceleration.sum(acelerationFromThisForce);
    }

    public void update (double dt){
       // v + (a * dt)
        Vector velocityChange = this.aceleration.multiEscalar(dt);
        this.velocity = this.velocity.sum(velocityChange);


        //pos = pos + (v * dt)

        Vector positionChange = this.velocity.multiEscalar(dt);

        this.position = this.position.sum(positionChange);

    }


}
