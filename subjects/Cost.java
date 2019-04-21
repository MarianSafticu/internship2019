package subjects;
public class Cost{
    private double fee;
    private double TVA;
    public Cost(double fee,double TVA){
        this.fee = fee;
        this.TVA = TVA;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getTVA() {
        return TVA;
    }

    public void setTVA(double TVA) {
        this.TVA = TVA;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "fee=" + fee +
                ", TVA=" + TVA +
                '}';
    }
}