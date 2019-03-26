
class Safe {

    double s1;
    double s2;
    double s3;

    Safe() {
    }

    Safe(double s1, double s2, double s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;

    }

    Safe(String s11, String s21, String s31) {
        s1 = Double.valueOf(s11);
        s2 = Double.valueOf(s21);
        s3 = Double.valueOf(s31);

    }
}
