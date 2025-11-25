import Model.Vector;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Vector v1 = new Vector(21, 4);
        Vector v2 = new Vector(10, 5);

        System.out.println("Soma: " + v1.sum(v2));
        System.out.println("Subtração: " + v1.subs(v2));
        System.out.println("Normalização v1: " + v1.normalizacao());
        System.out.println("Distancia: " + v1.diferenceTo(v2));


    }
}