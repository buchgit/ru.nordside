package nordside.model.order;

public class ClientOrderLineTO {
    //nomenclature title
    private String title;
    private String unit;
    private double count;
    private double summa;

    public ClientOrderLineTO(String title, String unit, Double count, Double summa) {
        this.title = title;
        this.unit = unit;
        this.count = count;
        this.summa = summa;
    }

    public ClientOrderLineTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public double getPrice(){
        return summa/count;  //todo проверить деление двух double
    }
}
