
class PAMP {

    double a1;
    double a2;
    double a3;
    double a4;
    double a5;

    PAMP() {
    }

    PAMP(double a1, double a2, double a3, double a4, double a5) {

        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
    }

    PAMP(String a1, String a2, String a3, String a4, String a5) {

        this.a1 = Double.valueOf(a1);
        this.a2 = Double.valueOf(a2);
        this.a3 = Double.valueOf(a3);
        this.a4 = Double.valueOf(a4);
        this.a5 = Double.valueOf(a5);
    }
}
