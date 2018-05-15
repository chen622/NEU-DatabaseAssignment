package account;

import data.Account;
import data.AccountTableProperty;
import data.DVDTableProperty;
import database.Operator;
import dvd.DVDController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import login.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class AccountController {
    @FXML
    TableView accountTable;
    @FXML
    TableColumn nameColumn;
    @FXML
    TableColumn categoryColumn;
    @FXML
    TableColumn balanceColumn;
    @FXML
    TableColumn amountColumn;
    @FXML
    TableColumn priceColumn;
    @FXML
    TextField accountid;

    private ObservableList<AccountTableProperty> accountData = FXCollections.observableArrayList();
    private boolean isInit = false;

    public void Init() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("category"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, Float>("balance"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, Integer>("amountUpToDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, Float>("priceUpToDate"));
        isInit = true;
        try {
            SetTable(Operator.accounts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(222);
    }


    public void ShowBooks(ActionEvent actionEvent) {
        try {
            Main.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("../dvd/dvd.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ShowBack(ActionEvent actionEvent) {
        try {
            URL location = getClass().getResource("../dvd/back.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();
            DVDController controller = fxmlLoader.getController();   //获取Controller的实例对象
            controller.Init();
            Main.getStage().setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ShowAccount(ActionEvent actionEvent) {
        try {
            Main.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("../account/account.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SetTable(ArrayList list) {

        accountData.clear();
        accountTable.setItems(null);
        Iterator<Account> iterator = list.iterator();
        Account account;
        while (iterator.hasNext()) {
            account = iterator.next();
            accountData.add(new AccountTableProperty(account.getName(), account.getCategory(), account.getAmountUpToDate(), account.getBalance(), account.getPriceUpToDate()));
        }
        accountTable.setItems(accountData);
    }

    public void Search(ActionEvent actionEvent) {
        try {
            if (isInit = false)
                Init();
            if (accountid.getText().length()<=0)
            SetTable(Operator.accounts());
            else
                SetTable(Operator.accounts(Integer.parseInt(accountid.getText())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
