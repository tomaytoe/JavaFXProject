package hu.alkfejl;

import hu.alkfejl.controller.QuizController;
import hu.alkfejl.controller.QuizControllerImpl;
import hu.alkfejl.view.addQuizDialog;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private final QuizController controller = new QuizControllerImpl();
    public static String azonosito = "";
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = createRoot();

        TextField input = new TextField("admin");
        input.setPromptText("felhasználónév");
        Button belep = new Button("Belépés");
        belep.setDefaultButton(true);
        root.getChildren().addAll(input, belep);

        belep.setOnAction(e -> {
            if(input.getText().isEmpty()){
                showWarning("Kérem adja meg az azonosítóját!");
                return;
            }
            else{
                azonosito = input.getText();
                primaryStage.close();
                new addQuizDialog(controller);
            }
        });

        Scene scene = new Scene(root, 300,110);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createRoot() {
        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundFill(Color.DIMGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setSpacing(10);
        root.setPadding(new Insets(10,10,10,10));
        root.setAlignment(Pos.CENTER);
        return root;
    }

    private void showWarning(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.setTitle("Hiba");
        alert.showAndWait();
    }

    public static String getAzonosito() {
        return azonosito;
    }

    public static void setAzonosito(String azonosito) {
        App.azonosito = azonosito;
    }
}
