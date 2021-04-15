package nordside.model.price;

import nordside.model.user.User;

public class UserPriceVariant {
    private User user;
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
