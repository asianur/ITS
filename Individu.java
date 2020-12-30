package cobasiah;
import java.util.Random;

public class Individu {
    
    int[] kromosom = null;
    Data data = null;
    double totalJarak = -1;
    double nilaiFitness = 0;
    
    Individu(Data data) {
        this.data = data;
    }
    
    public static int randomBetween(int min, int max) {
        if (min >= max) {
            //tukar
            int temp = min;
            min = max;
            max = temp;
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    public int[] generateRandomKromosom() {
        if (data != null && data.arrayVertex.length > 0){
            int n = data.arrayVertex.length;
            int min = 0;
            int max = n - 1;
            
            //random vertyex awal dan akhir
            int vertexAwal = randomBetween(min, max);
            int vertexAkhir = vertexAwal;
            
            //Kromosom
            //this.kromosom[n]. n karena panjang kromosom nya itu adalah n + 1, maka index terakhir adalah n
            this.kromosom = new int[n + 1];
            this.kromosom[0] = vertexAwal;//vertex awal
            this.kromosom[n] = vertexAkhir;//vertex akhir
            
            //random vertex antara
            //random vertex antara ini unik = tidak ada yang sama
            //merandom bisa berkali kali sampai ditemukan vertex yang tidak sama
            //jika telah menemukan vertex yang tidak sama, baru akan dimasukkan kedalam kromosom
            for (int i = 1; i < n; i++) {
                boolean sama = true;
                while (sama) {
                    int r = randomBetween(min, max);
                    sama = false;
                    for (int j = 0; j < i; j++) {
                        if (r == this.kromosom[j]) {
                            sama = true;
                            break;
                        }
                    }
                    if (sama == false) {
                        this.kromosom[i] = r;
                    }
                }
            }
        }
        return this.kromosom;
    }
    
    public double hitungTotalJarak(){
        //
        this.totalJarak = -1;
        if(kromosom!=null){
            double total = 0;
            for(int i=1;i<kromosom.length;i++){
                int indexVertex1 = kromosom[i-1];
                int indexVertex2 = kromosom[i];
                double jarak = data.hitungJarak(indexVertex1, indexVertex2);
                total += jarak;
            }
            this.totalJarak = total;
        }
        return this.totalJarak;
    }
    
    //total jarak harus lebih besar dari 0, agar tidak mengalami error logika
    public double hitungNilaiFitness(){
        this.nilaiFitness = 0;
        this.hitungTotalJarak();
        if(this.totalJarak>0){
            this.nilaiFitness = 1.0/this.totalJarak;
        }
        return this.nilaiFitness;
    }
    
    @Override
    public String toString(){
        String result = null;
        if(kromosom!=null){
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<kromosom.length;i++){
                if(i>0){
                    sb.append(" - ");
                }
                int indexVertex = kromosom[i];
                String label = data.arrayVertex[indexVertex].label;
                sb.append(label);
            }
            result = sb.toString();
        }
        return result;
    }

    
}
