package nordside.model.price;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nordside.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "user_price_variant")
public class UserPriceVariant {

    @OneToOne
    @JsonBackReference
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JsonBackReference
    @MapsId
    @JoinColumn(name = "price_variant")
    private PriceVariant priceVariant;

    public UserPriceVariant() {
    }

    public UserPriceVariant(User user, PriceVariant priceVariant) {
        this.user = user;
        this.priceVariant = priceVariant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PriceVariant getPriceVariant() {
        return priceVariant;
    }

    public void setPriceVariant(PriceVariant priceVariant) {
        this.priceVariant = priceVariant;
    }
}
