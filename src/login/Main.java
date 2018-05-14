package login;

import database.Database;
import database.Operator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dvd.DVDController;

import java.net.URL;

public class Main extends Application {
    static private Stage stage;
    public static Database database;

    public static Stage getStage() {
        return stage;
    }

    public static void main(String args[]){
        database = new Database();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("../dvd/dvd.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();

        stage = primaryStage;
        primaryStage.setTitle("Library System");
        primaryStage.setScene(new Scene(root));
        DVDController controller = fxmlLoader.getController();   //获取Controller的实例对象
        Operator.init();
        controller.Init();
        //Controller中写的初始化方法
        primaryStage.show();
    }
}
