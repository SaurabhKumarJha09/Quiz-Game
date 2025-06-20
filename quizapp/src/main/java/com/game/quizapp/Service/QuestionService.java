package com.game.quizapp.Service;

import com.game.quizapp.model.Question;
import com.game.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findAll(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category){
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question){
        questionDao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> addQuestions(List<Question> questionList){
        questionDao.saveAll(questionList);
        return new ResponseEntity<>("All questions are saved successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteAllQuestion(){
        questionDao.deleteAll();
        return new ResponseEntity<>("All Questions are deleted successfully ", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> deleteQuestionById(Integer id){
        questionDao.deleteById(id);
        return new ResponseEntity<>("A question is deleted successfully", HttpStatus.OK);
    }


}
