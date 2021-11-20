package nordside.model.order;

public class ClientOrderLine {
    //code in 1c
    private String code;
    //nomenclature title
    private String title;
    private String unit;
    private double count;
    private double summa;

    public ClientOrderLine(String code, String title, String unit, Double count, Double summa) {
        this.code = code;
        this.title = title;
        this.unit = unit;
        this.count = count;
        this.summa = summa;
    }

    public ClientOrderLine() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
