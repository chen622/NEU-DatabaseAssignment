package account;

import dvd.DVDController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import login.Main;

import java.io.IOException;
import java.net.URL;

public class AccountController {


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
}
