package data;

/**
 * Use to save the data of a user account
 */
public class Account {
    private String name;
    private String category;
    private float balance;
    private float priceUpToDate;
    private int amountUpToDate;

    public Account(String name, String category, float balance, float priceUpToDate, int amountUpToDate) {

        this.name = name;
        this.category = category;
        this.balance = balance;
        this.priceUpToDate = priceUpToDate;
        this.amountUpToDate = amountUpToDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getPriceUpToDate() {
        return priceUpToDate;
    }

    public void setPriceUpToDate(float priceUpToDate) {
        this.priceUpToDate = priceUpToDate;
    }

    public int getAmountUpToDate() {
        return amountUpToDate;
    }

    public void setAmountUpToDate(int amountUpToDate) {
        this.amountUpToDate = amountUpToDate;
    }
}
