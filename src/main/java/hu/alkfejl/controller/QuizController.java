package hu.alkfejl.controller;

import hu.alkfejl.model.bean.Quiz;

import java.util.List;

public interface QuizController {
    boolean addQuiz(Quiz p);
    List<Quiz> getAll();
    List<Quiz> getDateA();
    List<Quiz> getDateNA();
    List<Quiz> getFuture();
    List<Quiz> getFill();
}
