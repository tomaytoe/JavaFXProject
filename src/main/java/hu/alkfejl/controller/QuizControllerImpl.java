package hu.alkfejl.controller;

import hu.alkfejl.model.QuizDao;
import hu.alkfejl.model.QuizDaoDB;
import hu.alkfejl.model.bean.Quiz;

import java.util.List;

public class QuizControllerImpl implements QuizController {
    private final QuizDao dao = new QuizDaoDB();

    @Override
    public boolean addQuiz(Quiz p) {
        return dao.addQuiz(p);
    }

    @Override
    public List<Quiz> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Quiz> getDateA() {
        return dao.getDateA();
    }

    @Override
    public List<Quiz> getDateNA() {
        return dao.getDateNA();
    }

    @Override
    public List<Quiz> getFuture() {
        return dao.getFuture();
    }

    @Override
    public List<Quiz> getFill() {
        return dao.getFill();
    }
}
