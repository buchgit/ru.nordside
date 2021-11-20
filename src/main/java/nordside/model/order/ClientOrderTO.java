package nordside.model.order;

import java.util.*;

public class ClientOrderTO {
    private String id;
    private Date date;
    private String number_1c;
    private Double summa;
    private List<ClientOrderLine> orderLinesTable;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ClientOrderTO(String id, Date date, String number_1c, Double summa, List<ClientOrderLine> orderLinesTable) {
        this.id = id;
        this.date = date;
        this.number_1c = number_1c;
        this.summa = summa;
        this.orderLinesTable = orderLinesTable;
    }

    public ClientOrderTO() {
    }

    public double getTotalAmount(){
       return 0.00;
    }

    public double getTotalVolume(){
        return 0.00;
    }

    public double getTotalWeight(){
        return 0.00;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber_1c() {
        return number_1c;
    }

    public void setNumber_1c(String number_1c) {
        this.number_1c = number_1c;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }

    public List<ClientOrderLine> getOrderLinesTable() {
        return orderLinesTable;
    }

    public void setOrderLinesTable(List<ClientOrderLine> orderLinesTable) {
        this.orderLinesTable = orderLinesTable;
    }

}
