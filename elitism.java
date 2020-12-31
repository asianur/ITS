package cobasiah;

import java.io.File;

public class elitism {
    public static void main(String[] args) {
        File file = new File("burma14.tsp");
        Vertex[] arrayVertex = DataReader.read(file);
        Data data = new Data(arrayVertex);
        System.out.println(data.toString());
        
        Individu indv01 = new Individu(data);
        indv01.generateRandomKromosom();
        indv01.hitungNilaiFitness();
        
        System.out.print("TOUR: ");
        System.out.println(indv01.toString());
        System.out.println("total distance / jarak: "+indv01.totalJarak);
        System.out.println("fitness: "+indv01.nilaiFitness);
        
    }
}
