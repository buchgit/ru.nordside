package nordside.model.order;

import java.util.*;

public class ClientOrderTO {
    private Date date;
    private Double summa;
    private List<ClientOrderLineTO> orderLinesTable;

    public ClientOrderTO(Date date, Double summa, List<ClientOrderLineTO> orderLinesTable) {
        this.date = date;
        this.summa = summa;
        this.orderLinesTable = orderLinesTable;
    }

    public ClientOrderTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }

    public List<ClientOrderLineTO> getOrderLinesTable() {
        return orderLinesTable;
    }

    public void setOrderLinesTable(List<ClientOrderLineTO> orderLinesTable) {
        this.orderLinesTable = orderLinesTable;
    }

}
