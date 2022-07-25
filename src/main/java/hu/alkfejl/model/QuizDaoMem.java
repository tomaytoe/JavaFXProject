package hu.alkfejl.model;

import hu.alkfejl.model.bean.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizDaoMem implements QuizDao {
    private final List<Quiz> result = new ArrayList<>();

    @Override
    public boolean addQuiz(Quiz p) {
        result.add(p);
        return true;
    }

    @Override
    public List<Quiz> getAll() {
        return result;
    }

    @Override
    public List<Quiz> getDateA() {
        return result;
    }

    @Override
    public List<Quiz> getDateNA() {
        return result;
    }

    @Override
    public List<Quiz> getFuture() {
        return result;
    }

    @Override
    public List<Quiz> getFill() {
        return result;
    }
}
