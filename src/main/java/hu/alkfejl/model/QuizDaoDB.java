package hu.alkfejl.model;

import hu.alkfejl.model.bean.Quiz;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuizDaoDB implements QuizDao {
    //ObservableList options = FXCollections.observableArrayList();

    //private static final String DB_FILE = "jdbc:sqlite:quizDB.db";
    private static final String DB_FILE = "jdbc:sqlite:UserProject/QuizASPNET/QuizASPNET/DB/quizDB.db";

    private static final String INSERT_QUIZ =
            "INSERT INTO Quiz(nev, kitSzam, kitMikortol, kitMeddig, azon, maxKitIdo, kitoltve, link, kep) VALUES (?,?,?,?,?,?,?,?,?);";
    private static final String INSERT_FILL = "INSERT INTO Kitoltes(kitNev, kitIdeje, quiz_nev) VALUES (?,?,?)";

    private static final String QUERY_QUIZ = "SELECT nev FROM Quiz";

    private static String QUERY_QUESTION = "";
    private static String QUERY_ANSWER = "";
    private static String INSERT_QUESTION = "";
    private static String INSERT_ANSWER = "";
    private static final String UPDATE_QUIZ = "";
    private static final String UPDATE_QUESTION = "";
    private static final String UPDATE_ANSWER = "";
    private static String QUERY_QUESTION_TYPE = "";
    private static String QUERY_USER_TYPE = "";

    /**Aktuális idő lekérdezése a megfelelő dátum formátumban*/
    private static final LocalDate actualDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedString = actualDate.format(formatter);

   @Override
    public boolean addQuiz(Quiz p) {
        try(Connection conn = DriverManager.getConnection(DB_FILE);
            PreparedStatement ps = conn.prepareStatement(INSERT_QUIZ)) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getKitSzam());
            ps.setString(3, p.getKitMikortol());
            ps.setString(4, p.getKitMeddig());
            ps.setString(5, p.getAzon());
            ps.setInt(6, p.getMaxKitIdo());
            ps.setString(7, p.getKitoltve());
            ps.setString(8, p.getLink());
            ps.setString(9, p.getKep());

            int res = ps.executeUpdate();
            if(res == 1){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Quiz> getDateA() {
        List<Quiz> list = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_FILE);
            Statement st = conn.createStatement()){

            ResultSet rs = st.executeQuery("SELECT * FROM Quiz WHERE kitMeddig > '"+ formattedString +"'");

            while(rs.next()){
                Quiz quiz = new Quiz();
                quiz.setId_quiz(rs.getInt(1));
                quiz.setName(rs.getString(2));
                quiz.setKitSzam(rs.getInt(3));
                quiz.setKitMikortol(rs.getString(4));
                quiz.setKitMeddig(rs.getString(5));
                quiz.setAzon(rs.getString(6));
                quiz.setMaxKitIdo(rs.getInt(7));
                quiz.setKitoltve(rs.getString(8));
                quiz.setLink(rs.getString(8));
                quiz.setKep(rs.getString(10));
                list.add(quiz);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Quiz> getDateNA() {
        List<Quiz> list = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_FILE);
            Statement st = conn.createStatement()){

            ResultSet rs = st.executeQuery("SELECT * FROM Quiz WHERE kitMeddig < '"+ formattedString +"'");

            while(rs.next()){
                Quiz quiz = new Quiz();
                quiz.setId_quiz(rs.getInt(1));
                quiz.setName(rs.getString(2));
                quiz.setKitSzam(rs.getInt(3));
                quiz.setKitMikortol(rs.getString(4));
                quiz.setKitMeddig(rs.getString(5));
                quiz.setAzon(rs.getString(6));
                quiz.setMaxKitIdo(rs.getInt(7));
                quiz.setKitoltve(rs.getString(8));
                quiz.setLink(rs.getString(8));
                quiz.setKep(rs.getString(10));
                list.add(quiz);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Quiz> getFuture() {
        List<Quiz> list = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_FILE);
            Statement st = conn.createStatement()){

            ResultSet rs = st.executeQuery("SELECT * FROM Quiz WHERE kitMikortol > '"+ formattedString +"'");

            while(rs.next()){
                Quiz quiz = new Quiz();
                quiz.setId_quiz(rs.getInt(1));
                quiz.setName(rs.getString(2));
                quiz.setKitSzam(rs.getInt(3));
                quiz.setKitMikortol(rs.getString(4));
                quiz.setKitMeddig(rs.getString(5));
                quiz.setAzon(rs.getString(6));
                quiz.setMaxKitIdo(rs.getInt(7));
                quiz.setKitoltve(rs.getString(8));
                quiz.setLink(rs.getString(8));
                quiz.setKep(rs.getString(10));
                list.add(quiz);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Quiz> getFill() {
        List<Quiz> list = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_FILE);
            Statement st = conn.createStatement()){

            ResultSet rs = st.executeQuery("SELECT * FROM Quiz WHERE kitoltve = 'Igen';");

            while(rs.next()){
                Quiz quiz = new Quiz();
                quiz.setId_quiz(rs.getInt(1));
                quiz.setName(rs.getString(2));
                quiz.setKitSzam(rs.getInt(3));
                quiz.setKitMikortol(rs.getString(4));
                quiz.setKitMeddig(rs.getString(5));
                quiz.setAzon(rs.getString(6));
                quiz.setMaxKitIdo(rs.getInt(7));
                quiz.setKitoltve(rs.getString(8));
                quiz.setLink(rs.getString(8));
                quiz.setKep(rs.getString(10));
                list.add(quiz);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Quiz> getAll() {
        List<Quiz> list = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_FILE);
        Statement st = conn.createStatement()){

            ResultSet rs = st.executeQuery("SELECT * FROM Quiz;");

            while(rs.next()){
                Quiz quiz = new Quiz();
                quiz.setId_quiz(rs.getInt(1));
                quiz.setName(rs.getString(2));
                quiz.setKitSzam(rs.getInt(3));
                quiz.setKitMikortol(rs.getString(4));
                quiz.setKitMeddig(rs.getString(5));
                quiz.setAzon(rs.getString(6));
                quiz.setMaxKitIdo(rs.getInt(7));
                quiz.setKitoltve(rs.getString(8));
                quiz.setLink(rs.getString(8));
                quiz.setKep(rs.getString(10));
                list.add(quiz);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    /**Kérdés típusának lekérdezése*/
    public void queryQuestionType(ObservableList type, String question){
        QUERY_QUESTION_TYPE = "SELECT tipus FROM Kerdes WHERE kerdes = '"+ question +"'";

        try(Connection conn = DriverManager.getConnection(DB_FILE);
            PreparedStatement ps = conn.prepareStatement(QUERY_QUESTION_TYPE)) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                type.add(rs.getString("tipus"));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**Kérdőív készítőjének azonosítójának lekérdezése*/
    public void queryQuizCreator(ObservableList type, String question){
        QUERY_USER_TYPE = "SELECT azon FROM Quiz WHERE nev = '"+ question +"'";

        try(Connection conn = DriverManager.getConnection(DB_FILE);
            PreparedStatement ps = conn.prepareStatement(QUERY_USER_TYPE)) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                type.add(rs.getString("azon"));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**Megfelelő kérdésekhez a válaszok listázása*/
    public void FillComboBoxAnswer(ObservableList options, String question){
        QUERY_ANSWER = "SELECT valasz FROM Valasz WHERE kerdes_nev = '"+ question +"'";

        try(Connection conn = DriverManager.getConnection(DB_FILE);
            PreparedStatement ps = conn.prepareStatement(QUERY_ANSWER)) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                options.add(rs.getString("valasz"));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**Megfelelő kérdőívhez tartozó kérdések listázása*/
    public void FillComboBoxQuestion(ObservableList options, String question){
       QUERY_QUESTION = "SELECT kerdes FROM Kerdes WHERE quiz_nev = '"+ question +"'";

        try(Connection conn = DriverManager.getConnection(DB_FILE);
            PreparedStatement ps = conn.prepareStatement(QUERY_QUESTION)) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                options.add(rs.getString("kerdes"));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**Kérdőív név listázása*/
    public void FillComboBoxQuiz(ObservableList options){
        try(Connection conn = DriverManager.getConnection(DB_FILE);
            PreparedStatement ps = conn.prepareStatement(QUERY_QUIZ)) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                options.add(rs.getString("nev"));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**Kérdés hozzáadása*/
    public boolean AddQuestion(Quiz p){
        INSERT_QUESTION = "INSERT INTO Kerdes(quiz_nev, kerdes, tipus) VALUES (?,?,?)";

        try(Connection conn = DriverManager.getConnection(DB_FILE);
            PreparedStatement ps = conn.prepareStatement(INSERT_QUESTION)) {

            ps.setString(1, p.getQuiz_name_kerd());
            ps.setString(2, p.getKerdes());
            ps.setString(3, p.getTipus());

            int res = ps.executeUpdate();
            if(res == 1){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**Válasz hozzáadása*/
    public boolean AddAnswer(Quiz p){
        INSERT_ANSWER = "INSERT INTO Valasz(kerdes_nev, valasz) VALUES (?,?)";

        try(Connection conn = DriverManager.getConnection(DB_FILE);
            PreparedStatement ps = conn.prepareStatement(INSERT_ANSWER)) {

            ps.setString(1, p.getKerdes_nev());
            ps.setString(2, p.getValasz());

            int res = ps.executeUpdate();
            if(res == 1){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
