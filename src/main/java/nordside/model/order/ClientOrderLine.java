package nordside.model.order;

import nordside.model.AbstractBaseEntity;
import nordside.model.nomenclature.Nomenclature;

import javax.persistence.*;

@Entity
@Table(name = "client_order_line")
public class ClientOrderLine extends AbstractBaseEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    private ClientOrder clientOrder;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Nomenclature nomenclature;

    @Column
    private String unit;
    @Column
    private double count;
    @Column
    private double summa;

    /*
    id           integer primary key default nextval('global_seq'),
    order_id     integer not null,
    nomenclature_id integer not null,
    unit         varchar not null,
    count        double precision,
    summa        double precision,
     */

    public ClientOrderLine(ClientOrder clientOrder, Nomenclature nomenclature, String unit, double count, double summa) {
        this.clientOrder = clientOrder;
        this.nomenclature = nomenclature;
        this.unit = unit;
        this.count = count;
        this.summa = summa;
    }

    public ClientOrderLine() {
    }

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
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
}
