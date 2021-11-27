package nordside.model.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.util.*;

public class ClientOrderTO {
    private String id;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private Date localDate;
    private String number_1c;
    private Double summa;
    private List<ClientOrderLine> orderLinesTable;

    public Date getDate() {
        return localDate;
    }

    public void setDate(Date date) {
        this.localDate = date;
    }

    public ClientOrderTO(String id, Date date, String number_1c, Double summa, List<ClientOrderLine> orderLinesTable) {
        this.id = id;
        this.localDate = date;
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
