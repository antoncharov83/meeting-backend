package pro.profsoft.meetnetbackend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pro.profsoft.meetnetbackend.model.Question;
import pro.profsoft.meetnetbackend.model.dto.FormStatusDto;
import pro.profsoft.meetnetbackend.model.dto.hobby.QuestionHobbyDto;
import pro.profsoft.meetnetbackend.model.dto.hobby.QuestionHobbyResponse;
import pro.profsoft.meetnetbackend.model.dto.question.QuestionDto;
import pro.profsoft.meetnetbackend.service.ProfileAnswerService;
import pro.profsoft.meetnetbackend.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private QuestionService questionService;
    private ProfileAnswerService profileAnswerService;

    public QuestionController(QuestionService questionService, ProfileAnswerService profileAnswerService) {
        this.questionService = questionService;
        this.profileAnswerService = profileAnswerService;
    }

    @ApiOperation(value = "Вопросы",
            notes = "Возвращает все вопросы")
    @GetMapping
    public List<QuestionDto> getAll() {
        return questionService.getAll();
    }

    @ApiOperation(value = "Неотвеченные вопросы для анкеты",
            notes = "По id возвращает неотвеченные вопросы для анкеты")
    @GetMapping("form/unanswered-questions/{id}")
    public List<QuestionDto> getAllForFormByProfile(@PathVariable("id") Long id) {
        return questionService.getAllByProfile(id, Question.TypeQ.Form);
    }

    @ApiOperation(value = "Неотвеченные вопросы для теста",
            notes = "По id возвращает неотвеченные вопросы для теста")
    @GetMapping("test/unanswered-questions/{id}")
    public List<QuestionDto> getAllForTestByProfile(@PathVariable("id") Long id) {
        return questionService.getAllByProfile(id, Question.TypeQ.Test);
    }

    @ApiOperation(value = "Неотвеченные вопросы раздела хобби",
            notes = "По id возвращает неотвеченные вопросы раздела хобби")
    @GetMapping("/hobby")
    public QuestionHobbyResponse getHobbyResponse(@RequestHeader(value = "account-id") Long id) {
        return questionService.getAllHobby(id);
    }

    @ApiOperation(value = "Сохранение ответа",
            notes = "По id сохраняет ответ на вопрос")
    @PostMapping
    public void saveAnswer(@RequestHeader(value = "account-id") Long id, @RequestBody QuestionDto questionDto) {
        profileAnswerService.saveAnswer(id, questionDto);
    }

    @ApiOperation(value = "Сохранение ответа хобби",
            notes = "По id сохраняет ответ на вопрос хобби")
    @PostMapping("/hobby")
    public void saveAnswerHobby(@RequestHeader(value = "account-id") Long id,
                                @RequestBody QuestionHobbyDto questionHobbyDto) {
        profileAnswerService.saveAnswerHobby(id, questionHobbyDto);
    }

    @ApiOperation(value = "Вопрос для анкеты",
            notes = "По account-id профиля возвращает следующий неотвеченный вопрос >position")
    @GetMapping("form/get-next")
    public QuestionDto getFormQuestionNext(Long id, Integer position) {
        return questionService.getNext(id, Question.TypeQ.Form, position);
    }

    @ApiOperation(value = "Вопрос для теста",
            notes = "По account-id профиля возвращает следующий неотвеченный вопрос >position")
    @GetMapping("test/get-next")
    public QuestionDto getTestQuestionNext(Long id, Integer position) {
        return questionService.getNext(id, Question.TypeQ.Test, position);
    }

    @ApiOperation(value = "Статус анкеты и теста",
            notes = "Проверяет заполнен ли тест и анкета полностью, частично или не заполнен вообще")
    @GetMapping(value = "status", params = { "id" })
    public FormStatusDto getStatus(@RequestParam("id") Long id) {
        return questionService.getStatus(id);
    }

    @ApiOperation(value = "Технический",
            notes = "для быстрой заливки вопросов")
    @PostMapping("saveQuestions")
    public void saveQuestions(@RequestBody List<QuestionDto> questionDtoList) {
        questionService.saveQuestions(questionDtoList);
    }

    @ApiOperation(value = "Технический",
            notes = "для быстрой заливки вопросов хобби")
    @PostMapping("saveHobbies")
    public void saveHobbies(@RequestBody QuestionHobbyResponse questionHobbyResponse) {
        questionService.saveHobby(questionHobbyResponse);
    }
}
