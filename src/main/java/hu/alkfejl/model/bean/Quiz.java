package hu.alkfejl.model.bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Quiz {
    /**Quiz table*/
    private final IntegerProperty id_quiz = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty kitSzam = new SimpleIntegerProperty();
    private final StringProperty kitMikortol = new SimpleStringProperty();
    private final StringProperty kitMeddig = new SimpleStringProperty();
    private final StringProperty azon = new SimpleStringProperty();
    private final IntegerProperty maxKitIdo = new SimpleIntegerProperty();
    private final StringProperty kitoltve = new SimpleStringProperty();
    private final StringProperty link = new SimpleStringProperty();
    private final StringProperty kep = new SimpleStringProperty();

    /**KerdesValasz table*/
    private final IntegerProperty id_kerd = new SimpleIntegerProperty();
    private final StringProperty quiz_name_kerd = new SimpleStringProperty();
    private final StringProperty kerdes = new SimpleStringProperty();
    private final StringProperty tipus = new SimpleStringProperty();

    /**Valasz table*/
    private final IntegerProperty id_valasz = new SimpleIntegerProperty();
    private final StringProperty kerdes_nev = new SimpleStringProperty();
    private final StringProperty valasz = new SimpleStringProperty();

    /**Kitoltes table*/
    private final IntegerProperty id_kitolto = new SimpleIntegerProperty();
    private final StringProperty kit_nev = new SimpleStringProperty();
    private final StringProperty kit_ideje = new SimpleStringProperty();
    private final StringProperty quiz_name_kitolto = new SimpleStringProperty();

    public Quiz() {
    }

    public int getId_quiz() {
        return id_quiz.get();
    }

    public IntegerProperty id_quizProperty() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz.set(id_quiz);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getKitSzam() {
        return kitSzam.get();
    }

    public IntegerProperty kitSzamProperty() {
        return kitSzam;
    }

    public void setKitSzam(int kitSzam) {
        this.kitSzam.set(kitSzam);
    }

    public String getKitMikortol() {
        return kitMikortol.get();
    }

    public StringProperty kitMikortolProperty() {
        return kitMikortol;
    }

    public void setKitMikortol(String kitMikortol) {
        this.kitMikortol.set(kitMikortol);
    }

    public String getKitMeddig() {
        return kitMeddig.get();
    }

    public StringProperty kitMeddigProperty() {
        return kitMeddig;
    }

    public void setKitMeddig(String kitMeddig) {
        this.kitMeddig.set(kitMeddig);
    }

    public String getAzon() {
        return azon.get();
    }

    public StringProperty azonProperty() {
        return azon;
    }

    public void setAzon(String azon) {
        this.azon.set(azon);
    }

    public int getMaxKitIdo() {
        return maxKitIdo.get();
    }

    public IntegerProperty maxKitIdoProperty() {
        return maxKitIdo;
    }

    public void setMaxKitIdo(int maxKitIdo) {
        this.maxKitIdo.set(maxKitIdo);
    }

    public String getLink() {
        return link.get();
    }

    public StringProperty linkProperty() {
        return link;
    }

    public void setLink(String link) {
        this.link.set(link);
    }

    public String getKep() {
        return kep.get();
    }

    public StringProperty kepProperty() {
        return kep;
    }

    public void setKep(String kep) {
        this.kep.set(kep);
    }

    public int getId_kerd() {
        return id_kerd.get();
    }

    public IntegerProperty id_kerdProperty() {
        return id_kerd;
    }

    public void setId_kerd(int id_kerd) {
        this.id_kerd.set(id_kerd);
    }

    public String getQuiz_name_kerd() {
        return quiz_name_kerd.get();
    }

    public StringProperty quiz_name_kerdProperty() {
        return quiz_name_kerd;
    }

    public void setQuiz_name_kerd(String quiz_name_kerd) {
        this.quiz_name_kerd.set(quiz_name_kerd);
    }

    public String getKerdes() {
        return kerdes.get();
    }

    public StringProperty kerdesProperty() {
        return kerdes;
    }

    public void setKerdes(String kerdes) {
        this.kerdes.set(kerdes);
    }

    public String getValasz() {
        return valasz.get();
    }

    public StringProperty valaszProperty() {
        return valasz;
    }

    public void setValasz(String valasz) {
        this.valasz.set(valasz);
    }

    public int getId_kitolto() {
        return id_kitolto.get();
    }

    public IntegerProperty id_kitoltoProperty() {
        return id_kitolto;
    }

    public void setId_kitolto(int id_kitolto) {
        this.id_kitolto.set(id_kitolto);
    }

    public String getKit_nev() {
        return kit_nev.get();
    }

    public StringProperty kit_nevProperty() {
        return kit_nev;
    }

    public void setKit_nev(String kit_nev) {
        this.kit_nev.set(kit_nev);
    }

    public String getKit_ideje() {
        return kit_ideje.get();
    }

    public StringProperty kit_idejeProperty() {
        return kit_ideje;
    }

    public void setKit_ideje(String kit_ideje) {
        this.kit_ideje.set(kit_ideje);
    }

    public String getQuiz_name_kitolto() {
        return quiz_name_kitolto.get();
    }

    public StringProperty quiz_name_kitoltoProperty() {
        return quiz_name_kitolto;
    }

    public void setQuiz_name_kitolto(String quiz_name_kitolto) {
        this.quiz_name_kitolto.set(quiz_name_kitolto);
    }

    public String getKitoltve() {
        return kitoltve.get();
    }

    public StringProperty kitoltveProperty() {
        return kitoltve;
    }

    public void setKitoltve(String kitoltve) {
        this.kitoltve.set(kitoltve);
    }

    public String getTipus() {
        return tipus.get();
    }

    public StringProperty tipusProperty() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus.set(tipus);
    }

    public int getId_valasz() {
        return id_valasz.get();
    }

    public IntegerProperty id_valaszProperty() {
        return id_valasz;
    }

    public void setId_valasz(int id_valasz) {
        this.id_valasz.set(id_valasz);
    }

    public String getKerdes_nev() {
        return kerdes_nev.get();
    }

    public StringProperty kerdes_nevProperty() {
        return kerdes_nev;
    }

    public void setKerdes_nev(String kerdes_nev) {
        this.kerdes_nev.set(kerdes_nev);
    }
}
