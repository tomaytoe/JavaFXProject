package hu.alkfejl.view;

import hu.alkfejl.App;
import hu.alkfejl.controller.QuizController;
import hu.alkfejl.controller.QuizControllerImpl;
import hu.alkfejl.model.QuizDaoDB;
import hu.alkfejl.model.bean.Quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

public class addQuizQA {
    public addQuizQA(QuizController c) {addQuizQACreator(c);}
    private final QuizController controller = new QuizControllerImpl();
    final ObservableList questionsOP = FXCollections.observableArrayList();
    final ObservableList answerOP = FXCollections.observableArrayList();
    final ObservableList typeOL = FXCollections.observableArrayList();
    final ObservableList creatorOL = FXCollections.observableArrayList();

    private void addQuizQACreator(QuizController c){
        Stage stage = new Stage();

        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.DIMGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        GridPane questionGP = getGrid();
        GridPane answerGP = getGrid();

        HBox menuHB = getHBox();

        Quiz quiz = new Quiz();
        QuizDaoDB quizDB = new QuizDaoDB();

        QuizDaoDB comboQuestion = new QuizDaoDB();
        comboQuestion.FillComboBoxQuiz(questionsOP);
        ObservableList<String> optionsQ = FXCollections.observableArrayList(questionsOP);
        ComboBox<String> questions = new ComboBox<String>();
        questions.setItems(optionsQ); //értékek beállítása
        TextField questionTF = new TextField();

        ToggleGroup group = new ToggleGroup();
        RadioButton cBox1 = new RadioButton("Előre megadott szöveg");
        cBox1.setToggleGroup(group);
        RadioButton cBox2 = new RadioButton("Előre megadott dátum");
        cBox2.setToggleGroup(group);
        RadioButton cBox3 = new RadioButton("Nincs választásai lehetőség");
        cBox3.setToggleGroup(group);

        ToggleGroup group2 = new ToggleGroup();
        RadioButton cBox4 = new RadioButton("Egy választható");
        cBox4.setToggleGroup(group2);
        RadioButton cBox5 = new RadioButton("Több választható");
        cBox5.setToggleGroup(group2);

        QuizDaoDB user = new QuizDaoDB();
        App azon = new App();
        String azonSt = App.getAzonosito();
        Button saveQuestion = new Button("Kérdés mentése");
        saveQuestion.setOnAction(e-> {
            creatorOL.clear();
            user.queryQuizCreator(creatorOL, questions.getValue());

            if (azonSt.equals(creatorOL.get(0).toString())) {
                if (questions.getValue() == null) {
                       showWarning("A mező üres");
                       return;
                 }
              quiz.setQuiz_name_kerd(questions.getValue());

              if (questionTF.getText().isEmpty()) {
                    showWarning("A mező üres");
                    return;
              }
             quiz.setKerdes(questionTF.getText());

             if (!cBox1.isSelected() && !cBox2.isSelected() && !cBox3.isSelected()) {
                    showWarning("A mező üres!");
                    return;
              }
             if (cBox1.isSelected()) {
                 if(!cBox4.isSelected() && !cBox5.isSelected()){
                     showWarning("A mező üres!");
                     return;
                 }
                 else{
                     if(cBox4.isSelected()){
                         quiz.setTipus("TextOne");
                     }
                     if(cBox5.isSelected()){
                         quiz.setTipus("TextMore");
                     }
                 }
             } else if (cBox2.isSelected()) {
                    quiz.setTipus("Date");
             } else if (cBox3.isSelected()) {
                    quiz.setTipus("Empty");
             }

                quizDB.AddQuestion(quiz);
                questionTF.clear();
            }else{
                showWarning("Nincs jogosultsága a szerkesztésre!");
                return;
            }
        });

        questionGP.add(new Text("Kérdőív választása"),0,0);
        questionGP.add(questions,1,0);
        questionGP.add(new Text("Kérdés megadása"),0,1);
        questionGP.add(questionTF,1,1);
        questionGP.add(saveQuestion,0,2);
        questionGP.add(cBox1,0,3);
        questionGP.add(cBox4,1,3);
        questionGP.add(cBox2,0,4);
        questionGP.add(cBox5,1,4);
        questionGP.add(cBox3,0,5);

        QuizDaoDB comboAnswer = new QuizDaoDB();
        QuizDaoDB type = new QuizDaoDB();
        comboAnswer.FillComboBoxQuestion(answerOP, questions.getValue());
        ObservableList<String> optionsA = FXCollections.observableArrayList(answerOP);
        ComboBox<String> answer = new ComboBox<String>();
        answer.setItems(optionsA); //értékek beállítása
        TextField answerTF = new TextField();
        DatePicker dateAnswer = new DatePicker();
        Button saveAnswer = new Button("Válasz mentése");
        saveAnswer.setOnAction(e ->{
            if(answer.getValue() == null){
                showWarning("Nem választott ki kérdést!");
                return;
            }

            creatorOL.clear();
            user.queryQuizCreator(creatorOL, questions.getValue());

            if (azonSt.equals(creatorOL.get(0).toString())) {
                typeOL.clear();
                type.queryQuestionType(typeOL,answer.getValue());

                String textStOne = "TextOne";
                String textStMore = "TextMore";
                String dateSt = "Date";

                if(textStOne.equals(typeOL.get(0).toString()) || textStMore.equals(typeOL.get(0).toString())){
                    if(answerTF.getText().isEmpty()){
                        showWarning("A mező üres");
                        return;
                    }
                    quiz.setValasz(answerTF.getText());
                    quiz.setKerdes_nev(answer.getValue());
                    quizDB.AddAnswer(quiz);
                }
                else if(dateSt.equals(typeOL.get(0).toString())){
                    if(dateAnswer.getValue() == null){
                        showWarning("A mező üres");
                        return;
                    }
                    String dateString = dateAnswer.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    quiz.setValasz(dateString);
                    quiz.setKerdes_nev(answer.getValue());
                    quizDB.AddAnswer(quiz);
                }
                else{
                    showWarning("Ehhez a kérdéshez nem tartozhat válasz!");
                    return;
                }

                answerTF.clear();
            }
            else{
                showWarning("Nincs jogosultsága a szerkesztésre!");
                return;
            }
        });

        answerGP.add(new Text("Kérdés választása"),0,0);
        answerGP.add(answer,1,0);
        answerGP.add(new Text("Válasz megadása szövegként"),0,1);
        answerGP.add(answerTF,1,1);
        answerGP.add(new Text("Válasz megadása dátumként"),0,2);
        answerGP.add(dateAnswer,1,2);
        answerGP.add(saveAnswer,0,3);

        Button refresh = new Button("Frissít");
        refresh.setOnAction(e ->{
            answerOP.clear();
            comboAnswer.FillComboBoxQuestion(answerOP, questions.getValue());
            answer.setItems(answerOP);
        });
        Button cancel = new Button("Kilépés");
        cancel.setOnAction(e->{
            stage.close();
        });
        menuHB.getChildren().addAll(refresh,cancel);

        root.setLeft(questionGP);
        root.setRight(answerGP);
        root.setBottom(menuHB);

        Scene scene = new Scene(root,750,250);
        stage.setScene(scene);
        stage.setTitle("Kérdés & Válasz hozzáadása");
        stage.show();
    }

    private HBox getHBox() {
        HBox menuHB = new HBox();
        menuHB.setSpacing(10);
        menuHB.setPadding(new Insets(10,10,10,10));
        menuHB.setAlignment(Pos.CENTER);
        menuHB.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
        return menuHB;
    }

    private GridPane getGrid() {
        GridPane questionGP = new GridPane();
        questionGP.setBackground(new Background(new BackgroundFill(Color.DIMGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        questionGP.setVgap(10);
        questionGP.setHgap(10);
        questionGP.setPadding(new Insets(10));
        return questionGP;
    }

    private void showWarning(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.setTitle("Hiba");
        alert.showAndWait();
    }

}
