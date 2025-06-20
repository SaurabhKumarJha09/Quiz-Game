package com.game.quizapp.Controller;

import com.game.quizapp.model.Question;
import com.game.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestion")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add-Question")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }

    @PostMapping("add-Questions")
    public ResponseEntity<String> addQuestions(@RequestBody List<Question> questionList){
        return questionService.addQuestions(questionList);
    }

    @DeleteMapping("delete-all-Questions")
    public ResponseEntity deleteAllQuestion(){
        return questionService.deleteAllQuestion();
    }


    @DeleteMapping("delete-question/{id}")
    public ResponseEntity deleteQuestionById(Integer id){
        return questionService.deleteQuestionById(id);
    }


}


//    @PutMapping
//filter-by ---> 1) date,2) time, 3) price, 4) name