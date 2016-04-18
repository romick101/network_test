package GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Controller c = new Controller();
        primaryStage.setTitle("Chat");
        primaryStage.setScene(c.createScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
