
class Danger {

    double d1;
    double d2;

    Danger() {
    }

    Danger(double d1, double d2) {
        this.d1 = d1;
        this.d2 = d2;
    }

    Danger(String d1, String d2) {
        this.d1 = Double.valueOf(d1);
        ;
        this.d2 = Double.valueOf(d2);
        ;
    }
}
