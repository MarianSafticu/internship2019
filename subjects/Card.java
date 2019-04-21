package subjects;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Card{
    private String name;
    private double fee;
    private double withdrawLimit;
    private LocalDate expiration;
    private double available;
    public Card(String name,double fee,double withdrawLimit, String expiration,  double available){
        this.name = name;
        this.fee = fee;
        this.withdrawLimit = withdrawLimit;
        this.expiration = LocalDate.parse(expiration, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.available = available;
    }
    public boolean isValid(LocalDate today){
        return expiration.isAfter(today);
    }
    public String getName(){
        return this.name;
    }
    public double getFee(){
        return this.fee;
    }
    public double getWithdrawLimit(){
        return this.withdrawLimit;
    }
    public LocalDate getExpirationDate(){
        return this.expiration;
    }
    public double getAvailable(){
        return this.available;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setFee(double fee){
        this.fee = fee;
    }
    public void setWithdrawLimit(double withdrawLimit){
        this.withdrawLimit = withdrawLimit;
    }
    public void setExpirationDate(LocalDate expiration){
        this.expiration = expiration;
    }
    public void setExpirationDate(String expiration){
        LocalDate.parse(expiration, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", fee=" + fee +
                ", withdrawLimit=" + withdrawLimit +
                ", expiration=" + expiration +
                ", available=" + available +
                '}';
    }
}