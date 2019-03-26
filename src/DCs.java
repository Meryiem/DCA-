
import java.util.Random;

class DCs {

    String Etat;// mature, immature, semi-mature
    int SeulM;//entre 100 et 300
    int Context;//1 ou 0

    DCs() {

        Etat = "immature";
        Random r = new Random();
        SeulM = r.nextInt(200) + 100;
        Context = -1;
    }
}
