package account;

import data.DVDTableProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import login.Main;

import java.io.IOException;
import java.net.URL;

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

    private ObservableList<DVDTableProperty> accountData = FXCollections.observableArrayList();


    public void Init() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("category"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, Float>("balance"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, Integer>("amountUpToDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, Float>("priceUpToDate"));

        //        SetTable(null);
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

    public void SetTable(DVDTableProperty[] list) {
        accountData.clear();
        accountTable.setItems(null);
        accountData.addAll(list);
        accountTable.setItems(accountData);
        accountTable.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow call(TableView param) {
                TableRow tableRow = new TableRow();
                tableRow.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println(tableRow.getIndex()+"  "+accountData.size());
                    }
                });
                return tableRow;
            }
        });
    }
}
