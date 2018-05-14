package data;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AccountTableProperty {
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty category = new SimpleStringProperty();
    private final SimpleIntegerProperty amountUpToDate = new SimpleIntegerProperty();
    private final SimpleFloatProperty balance = new SimpleFloatProperty();
    private final SimpleFloatProperty priceUpToDate =new SimpleFloatProperty();

    public AccountTableProperty(String name,String category,int amountUpToDate,float balance,float priceUpToData) {
        setAmountUpToDate(amountUpToDate);
        setBalance(balance);
        setCategory(category);
        setName(name);
        setPriceUpToDate(priceUpToData);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getAmountUpToDate() {
        return amountUpToDate.get();
    }

    public SimpleIntegerProperty amountUpToDateProperty() {
        return amountUpToDate;
    }

    public void setAmountUpToDate(int amountUpToDate) {
        this.amountUpToDate.set(amountUpToDate);
    }

    public float getBalance() {
        return balance.get();
    }

    public SimpleFloatProperty balanceProperty() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance.set(balance);
    }

    public float getPriceUpToDate() {
        return priceUpToDate.get();
    }

    public SimpleFloatProperty priceUpToDateProperty() {
        return priceUpToDate;
    }

    public void setPriceUpToDate(float priceUpToDate) {
        this.priceUpToDate.set(priceUpToDate);
    }
}
