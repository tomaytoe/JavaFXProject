package hu.alkfejl.view;

import hu.alkfejl.App;
import hu.alkfejl.model.bean.Quiz;
import hu.alkfejl.controller.QuizController;
import hu.alkfejl.controller.QuizControllerImpl;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;

public class addQuizDialog {
    public addQuizDialog(QuizController c) {createDialog(c);}
    private final QuizController controller = new QuizControllerImpl();
    private final MenuBar menuBar = new MenuBar();
    private final TableView<Quiz> table = new TableView<>();
    BorderPane root = new BorderPane();

    private final String inputFilePath = "";
    private String encodedString = "";
    
    private void createDialog(QuizController c){
        Stage stage = new Stage();

        Menu quizMenu = new Menu("Kérdőív");
        menuBar.getMenus().add(quizMenu);
        MenuItem addOptionsQuizMenuItem = new MenuItem("Kérdés/Válaszok hozzáadása");
        addOptionsQuizMenuItem.setOnAction(e -> {
            new addQuizQA(controller);
        });
        quizMenu.getItems().add(addOptionsQuizMenuItem);

        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.DIMGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        TextField quizNevTF = new TextField();
        quizNevTF.setPromptText("kérdőív neve");

        DatePicker mikortolTF = new DatePicker();
        DatePicker meddigTF = new DatePicker();
        TextField hanyszorTF = new TextField();
        TextField maxKitIdoTF = new TextField();
        hanyszorTF.setPromptText("kitöltések száma");
        TextField linkTF = new TextField();
        linkTF.setPromptText("link");
        Button imgButton = new Button("Keresés");
        imgButton.setOnAction(e->{
            FileChooser fileChooser1 = new FileChooser();
            fileChooser1.setTitle("Kép betöltése");
            File fileimg = fileChooser1.showOpenDialog(stage);

            try{
                byte[] fileContent = FileUtils.readFileToByteArray(fileimg);
                encodedString = Base64
                        .getEncoder()
                        .encodeToString(fileContent);
            }
            catch(IOException ioe){
                showWarning("Probléma a kép kiválasztásával!");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kép kiválasztása");
            alert.setHeaderText("Sikeresen kiválasztotta az alábbi képet:");
            alert.setContentText(fileimg.toString());
            alert.showAndWait();
        });

        gridPane.add(new Text("A kérdőív neve*"), 0, 0);
        gridPane.add(quizNevTF, 1, 0);
        gridPane.add(new Text("Kép"), 0, 1);
        gridPane.add(imgButton, 1, 1);
        gridPane.add(new Text("Mikortól kitölthető*"), 0, 2);
        gridPane.add(mikortolTF, 1, 2);
        gridPane.add(new Text("Meddig kitölthető*"), 0, 3);
        gridPane.add(meddigTF, 1, 3);
        gridPane.add(new Text("Hányszor tölthető ki*"), 0, 4);
        gridPane.add(hanyszorTF, 1, 4);
        gridPane.add(new Text("Maximális kitöltési idő (perc)*"), 0, 5);
        gridPane.add(maxKitIdoTF, 1, 5);
        gridPane.add(new Text("Link:"), 0, 6);
        gridPane.add(linkTF, 1, 6);

        Button saveButton = new Button("Mentés");
        saveButton.setDefaultButton(true);
        saveButton.setOnAction(e -> {
            //A gomb lenyomasanak hatasara validalja az adatokat
            if(quizNevTF.getText().isEmpty()){
                showWarning("A mező üres");
                return;
            }

            if(mikortolTF.getValue() == null){
                showWarning("A mező üres");
                return;
            }

            if(meddigTF.getValue() == null){
                showWarning("A mező üres");
                return;
            }

            int hanyszor;
            if(hanyszorTF.getText().isEmpty()){
                showWarning("A mező üres!");
                return;
            }
            try{
                hanyszor = Integer.parseInt(hanyszorTF.getText());
            }
            catch(NumberFormatException nfe){
                showWarning("Számnak kell lennie!");
                return;
            }

            int maxido;
            if(maxKitIdoTF.getText().isEmpty()){
                showWarning("A mező üres!");
                return;
            }
            try{
                maxido = Integer.parseInt(maxKitIdoTF.getText());
            }
            catch(NumberFormatException nfe){
                showWarning("Számnak kell lennie!");
                return;
            }

            String mikortolDate = mikortolTF.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String meddigDate = meddigTF.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            App azon = new App();

            //Majd menti oket ha minden jol van kitoltve
            Quiz quiz = new Quiz();
            quiz.setName(quizNevTF.getText());
            quiz.setKitSzam(hanyszor);
            quiz.setKitMikortol(mikortolDate);
            quiz.setKitMeddig(meddigDate);
            quiz.setAzon(App.getAzonosito());
            quiz.setMaxKitIdo(maxido);
            quiz.setKitoltve("Nem");
            quiz.setKep(encodedString);

             if(c.addQuiz(quiz)){
                 table.setItems(FXCollections.observableArrayList(controller.getAll()));
             } else {
             showWarning("Hiba történt!");
             return;
             }
        });
        gridPane.add(saveButton, 0, 7);

        createTable();

        ToggleGroup group = new ToggleGroup();
        RadioButton cBox1 = new RadioButton("Lejárt");
        cBox1.setToggleGroup(group);
        RadioButton cBox2 = new RadioButton("Aktuális");
        cBox2.setToggleGroup(group);
        RadioButton cBox3 = new RadioButton("Jövőbeni");
        cBox3.setToggleGroup(group);
        RadioButton cBox4 = new RadioButton("Kitöltött");
        cBox4.setToggleGroup(group);
        RadioButton cBox5 = new RadioButton("Összes");
        cBox5.setToggleGroup(group);

        Button list = new Button("Listázás");
        list.setOnAction(e ->{
            if(cBox1.isSelected()){
                table.setItems(FXCollections.observableArrayList(controller.getDateNA()));
            }
            if(cBox2.isSelected()){
                table.setItems(FXCollections.observableArrayList(controller.getDateA()));
            }
            if(cBox3.isSelected()){
                table.setItems(FXCollections.observableArrayList(controller.getFuture()));
            }
            if(cBox4.isSelected()){
                table.setItems(FXCollections.observableArrayList(controller.getFill()));
            }
            if(cBox5.isSelected()){
                table.setItems(FXCollections.observableArrayList(controller.getAll()));
            }
        });
        Button export = new Button("Exportálás");
        export.setOnAction(e->{

            String file = "";

            if(!cBox1.isSelected() && !cBox2.isSelected() && !cBox3.isSelected() && !cBox4.isSelected() && !cBox5.isSelected()){
                showWarning("Listázás szükséges az exportáláshoz!");
                return;
            }

            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);

            file = selectedDirectory.toString() + "Quiz.csv.";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exportálás");
            alert.setHeaderText("Sikeres exportálás");
            alert.setContentText("A kívánt listázás eredménye megtalható a mentett helyen!");
            alert.showAndWait();

            System.out.println(file);

            if(cBox1.isSelected()){
                try {
                    writeExcelNA(file);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }
            if(cBox2.isSelected()){
                try {
                    writeExcelA(file);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }
            if(cBox3.isSelected()){
                try {
                    writeExcelFuture(file);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }
            if(cBox4.isSelected()){
                try {
                    writeExcelFill(file);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }
            if(cBox5.isSelected()){
                try {
                    writeExcelAll(file);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }

        });

        HBox listing = new HBox(cBox1, cBox2, cBox3, cBox4, cBox5, list, export);
        listing.setSpacing(10);
        listing.setPadding(new Insets(10,10,10,10));

        VBox tableVB = new VBox(table, listing);
        tableVB.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
        tableVB.setSpacing(10);

        root.setTop(menuBar);
        root.setLeft(gridPane);
        root.setCenter(tableVB);

        Scene scene = new Scene(root, 1000,500);

        stage.setScene(scene);
        stage.setTitle("Kérdőív hozzáadása");
        stage.show();

        ButtonType buttonTypeYes = new ButtonType("Igen", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Nem", ButtonBar.ButtonData.NO);
        stage.setOnCloseRequest(e -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, null, buttonTypeYes, buttonTypeNo);
            confirm.setTitle("Kilépés");
            confirm.setHeaderText("Biztosan ki szeretne lépni?");
            Optional<ButtonType> answer = confirm.showAndWait();
            if (answer.get() == ButtonType.NO) {
                e.consume();
            }
        });

    }

    private void createTable() {
        table.setEditable(false);

        TableColumn<Quiz, String> nameCol = new TableColumn<>("Kérdőív neve");
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Quiz, Integer> yearCol = new TableColumn<>("Maximális kitöltés");
        yearCol.setCellValueFactory(data -> data.getValue().kitSzamProperty().asObject());

        TableColumn<Quiz, String> datemikorCol = new TableColumn<>("Kitöltés kezdete");
        datemikorCol.setCellValueFactory(data -> data.getValue().kitMikortolProperty());

        TableColumn<Quiz, String> datemeddigCol = new TableColumn<>("Kitöltés vége");
        datemeddigCol.setCellValueFactory(data -> data.getValue().kitMeddigProperty());

        TableColumn<Quiz, Integer> maxkitolt = new TableColumn<>("Kitöltési idő (percben)");
        maxkitolt.setCellValueFactory(data -> data.getValue().maxKitIdoProperty().asObject());

        TableColumn<Quiz, String> kitoltve = new TableColumn<>("Kitöltött-e");
        kitoltve.setCellValueFactory(data -> data.getValue().kitoltveProperty());


        table.getColumns().addAll(nameCol, yearCol, datemikorCol, datemeddigCol,maxkitolt,kitoltve);
        table.setItems(FXCollections.observableArrayList(controller.getAll()));
    }

    private void showWarning(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.setTitle("Hiba");
        alert.showAndWait();
    }

    public void writeExcelA(String toFile) throws Exception {
        Writer writer = null;
        try {
            File file = new File(toFile);
            writer = new BufferedWriter(new FileWriter(file));
            for (Quiz q : controller.getDateA()) {

                String text = q.getName() + "," + q.getMaxKitIdo() + "," + q.getKitMikortol() + "," + q.getKitMeddig()+ "," + q.getKit_ideje()+ "," + q.getKitoltve() + "\n";

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }

    public void writeExcelNA(String toFile) throws Exception {
        Writer writer = null;
        try {
            File file = new File(toFile);
            writer = new BufferedWriter(new FileWriter(file));
            for (Quiz q : controller.getDateNA()) {

                String text = q.getName() + "," + q.getMaxKitIdo() + "," + q.getKitMikortol() + "," + q.getKitMeddig()+ "," + q.getKit_ideje()+ "," + q.getKitoltve() + "\n";

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }

    public void writeExcelFuture(String toFile) throws Exception {
        Writer writer = null;
        try {
            File file = new File(toFile);
            writer = new BufferedWriter(new FileWriter(file));
            for (Quiz q : controller.getFuture()) {

                String text = q.getName() + "," + q.getMaxKitIdo() + "," + q.getKitMikortol() + "," + q.getKitMeddig()+ "," + q.getKit_ideje()+ "," + q.getKitoltve() + "\n";

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }

    public void writeExcelFill(String toFile) throws Exception {
        Writer writer = null;
        try {
            File file = new File(toFile);
            writer = new BufferedWriter(new FileWriter(file));
            for (Quiz q : controller.getFill()) {

                String text = q.getName() + "," + q.getMaxKitIdo() + "," + q.getKitMikortol() + "," + q.getKitMeddig()+ "," + q.getKit_ideje()+ "," + q.getKitoltve() + "\n";

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }

    public void writeExcelAll(String toFile) throws Exception {
        Writer writer = null;
        try {
            File file = new File(toFile);
            writer = new BufferedWriter(new FileWriter(file));
            for (Quiz q : controller.getAll()) {

                String text = q.getName() + "," + q.getMaxKitIdo() + "," + q.getKitMikortol() + "," + q.getKitMeddig()+ "," + q.getKit_ideje()+ "," + q.getKitoltve() + "\n";

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }
}

