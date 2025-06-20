package com.game.quizapp.Service;

import com.game.quizapp.dao.QuestionDao;
import com.game.quizapp.dao.QuizDao;
import com.game.quizapp.model.Question;
import com.game.quizapp.model.QuestionWrapper;
import com.game.quizapp.model.Quiz;
import com.game.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService  {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id){
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestion();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);

        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestion();
        int right = 0;
        int i =0;
        for(Response response : responses){
            if(response.getResponse().equals(questions. get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }


//    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//        // Fetch quiz by ID
//        Quiz quiz = quizDao.findById(id).orElse(null);
//        if (quiz == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        List<Question> questions = quiz.getQuestion();
//
//        // Map questions by their ID for quick lookup
//        Map<Integer, Question> questionMap = questions.stream()
//                .collect(Collectors.toMap(Question::getId, q -> q));
//
//        int right = 0;
//
//        // Iterate through each response to check against the corresponding question
//        for (Response response : responses) {
//            Question question = questionMap.get(response.getId());
//
//            // Check if the question exists in the quiz
//            if (question != null) {
//                String correctAnswer = question.getRightAnswer().trim().toLowerCase();
//                String userAnswer = response.getResponse().trim().toLowerCase();
//
//                System.out.println("Comparing Question ID: " + question.getId() +
//                        " | Correct Answer: " + correctAnswer +
//                        " | User Answer: " + userAnswer);
//
//                // Compare answers
//                if (correctAnswer.equals(userAnswer)) {
//                    right++;
//                }
//            }
//        }
//
//        return new ResponseEntity<>(right, HttpStatus.OK);
//    }





}
