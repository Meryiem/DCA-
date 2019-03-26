
import java.util.Vector;

class Entrer {

    Antigene antigene;
    PAMP pamp;
    Danger danger;
    Safe safe;
    boolean Naromal;
    int MCAV = -1;
    Vector<DCs> dcs;
    int Semi_Mature, Mature;

    Entrer() {
    }

    Entrer(Antigene antigene, Safe safe, Danger danger, PAMP pamp) {
        this.antigene = antigene;
        this.pamp = pamp;
        this.danger = danger;
        this.safe = safe;
        dcs = new Vector<DCs>();
    }
}
