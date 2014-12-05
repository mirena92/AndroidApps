package android.hackbulgaria.com.expenselist;

/**
 * Created by RUSHI on 3.12.2014 г..
 */
public class Expence {

    public String label;
    public String price;

    Expence(String s1, String s2) {
        label = s1;
        price = s2;
    }
    Expence() {
    }

    public String getLabel() {
        return label;
    }

    public String getPrice() {
        return price;
    }
}
