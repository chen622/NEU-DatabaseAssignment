package dvd;

import data.DVD;
import data.DVDTableProperty;
import database.Operator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import login.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class DVDController {
    @FXML
    TableView dvdTable;
    @FXML
    TableColumn IdColumn;
    @FXML
    TableColumn TitleColumn;
    @FXML
    TableColumn ReleaseYearColumn;
    @FXML
    TableColumn DirectorColumn;
    @FXML
    TableColumn GenreColumn;
    @FXML
    TableColumn CastColumn;
    @FXML
    TableColumn LibraryColumn;
    @FXML
    ChoiceBox genre;
    @FXML
    TextField dvdLibrary;
    @FXML
    TextField dvdTitle;
    @FXML
    VBox backBookItem;
    @FXML
    Label bookName;
    @FXML
    Label renterName;
    @FXML
    Label fromDate;
    @FXML
    Label library;

    private ObservableList<DVDTableProperty> dvdData = FXCollections.observableArrayList();

    public void Init() {
        IdColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, Integer>("dvdId"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("title"));
        ReleaseYearColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("releaseYear"));
        DirectorColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("director"));
        GenreColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("genre"));
        CastColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("cast"));
        LibraryColumn.setCellValueFactory(new PropertyValueFactory<DVDTableProperty, String>("library"));

        try {
            SetTable(Operator.dvds(null,null,null));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(111);

        genre.setItems(FXCollections.observableArrayList("comedy", "action", "horror", "romance", "factual"));

    }

    public void ShowBooks(ActionEvent actionEvent) {
        try {
            Main.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("dvd.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ShowBack(ActionEvent actionEvent) {
        try {
            Main.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("back.fxml"))));
            Init();
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

    public void SetTable(ArrayList<DVD> list) {
        dvdData.clear();
        dvdTable.setItems(null);
        Iterator<DVD> iterator = list.iterator();
        DVD dvd;
        while (iterator.hasNext()){
            dvd = iterator.next();
            dvdData.add(new DVDTableProperty(dvd.getDvdId(),dvd.getTitle(),dvd.getReleaseYear(),dvd.getDirector(),dvd.getGenre(),dvd.getCast(),dvd.getLibrary()));
        }
        dvdTable.setItems(dvdData);
        dvdTable.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow call(TableView param) {
                TableRow tableRow = new TableRow();
                tableRow.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println(tableRow.getIndex()+"  "+dvdData.size());
                    }
                });
                return tableRow;
            }
        });
    }

    public void SearchDVD(ActionEvent actionEvent) {
        String[] array = {null,null,null};
        if (dvdLibrary.getText()!=null){
            array[0] = dvdLibrary.getText();
        }
        if (dvdTitle.getText()!=null){
            array[1] = dvdTitle.getText();
        }
        if (genre.getValue()!=null){
            array[2] = (String)genre.getValue();
        }
        try {
            SetTable(Operator.dvds(array[0],array[1],array[2]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SearchRental(ActionEvent actionEvent) {
        bookName.setText("yudaiyang");
        renterName.setText("ydy");
        fromDate.setText("date");
        library.setText("llg");
        backBookItem.setEffect(null);
    }

    public void Return(ActionEvent actionEvent) {

    }
}
