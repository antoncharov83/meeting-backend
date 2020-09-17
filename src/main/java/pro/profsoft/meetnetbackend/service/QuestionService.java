package pro.profsoft.meetnetbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pro.profsoft.meetnetbackend.exception.EntityNotFoundException;
import pro.profsoft.meetnetbackend.mapper.QuestionHobbyMapper;
import pro.profsoft.meetnetbackend.mapper.QuestionMapper;
import pro.profsoft.meetnetbackend.model.*;
import pro.profsoft.meetnetbackend.model.dto.FormStatusDto;
import pro.profsoft.meetnetbackend.model.dto.hobby.QuestionHobbyResponse;
import pro.profsoft.meetnetbackend.model.dto.question.QuestionDto;
import pro.profsoft.meetnetbackend.repository.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private QuestionRepository repository;
    private AnswerRepository answerRepository;
    private AnswerRangeRepository answerRangeRepository;
    private QuestionMapper questionMapper;
    private QuestionHobbyRepository questionHobbyRepository;
    private QuestionHobbyMapper questionHobbyMapper;
    private ProfileAnswerRepository profileAnswerRepository;
    private AnswerHobbyRepository answerHobbyRepository;
    private ProfileRepository profileRepository;
    private AnswerMatchRepository answerMatchRepository;

    public QuestionService(QuestionRepository repository, AnswerRepository answerRepository,
                           AnswerRangeRepository answerRangeRepository, QuestionMapper questionMapper,
                           QuestionHobbyRepository questionHobbyRepository,
                           QuestionHobbyMapper questionHobbyMapper,
                           ProfileAnswerRepository profileAnswerRepository,
                           AnswerHobbyRepository answerHobbyRepository,
                           ProfileRepository profileRepository,
                           AnswerMatchRepository answerMatchRepository) {
        this.repository = repository;
        this.answerRepository = answerRepository;
        this.answerRangeRepository = answerRangeRepository;
        this.questionMapper = questionMapper;
        this.questionHobbyRepository = questionHobbyRepository;
        this.questionHobbyMapper = questionHobbyMapper;
        this.profileAnswerRepository = profileAnswerRepository;
        this.answerHobbyRepository = answerHobbyRepository;
        this.profileRepository = profileRepository;
        this.answerMatchRepository = answerMatchRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<QuestionDto> getAll() {
        return repository.getAll()
                .stream()
                .map(q -> questionMapper.questionToQuestionDto(q))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<QuestionDto> getAllByProfile(Long id, Question.TypeQ typeQ) {
        List<Question> questions = repository.getAllByProfile(id, typeQ);
        if (typeQ.equals(Question.TypeQ.Form)) {
            questions = filterBySex(id, questions);
        }
        return questions.stream()
                .map(q -> questionMapper.questionToQuestionDto(q))
                .collect(Collectors.toList());
    }

    public List<Question> filterBySex(Long id, List<Question> questions) {
        Optional<ProfileAnswer> profileAnswer = profileAnswerRepository.getSex(id);
        if (profileAnswer.isPresent()) {
            if (profileAnswer.get().getAnswer().getText().equalsIgnoreCase("Мужчина")) {
                return questions.stream()
                        .filter(q -> !q.getPosition().equals(26))
                        .collect(Collectors.toList());
            } else {
                return questions.stream()
                        .filter(q -> !q.getPosition().equals(27))
                        .collect(Collectors.toList());
            }
        } else {
            return questions.stream()
                    .filter(q -> !q.getPosition().equals(26) && !q.getPosition().equals(27))
                    .collect(Collectors.toList());
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public QuestionDto getNext(Long id, Question.TypeQ typeQ, Integer position) {
        return questionMapper.questionToQuestionDto(repository.getNextQuestion(id, typeQ, position));
    }

    public FormStatusDto getStatus(Long id) {
        profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " profile not found"));
        FormStatusDto formStatusDto = new FormStatusDto();
        formStatusDto.setTestFormStatus(statusTestFilled(id));
        formStatusDto.setInformationFormStatus(statusFormFilled(id));
        return formStatusDto;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public int statusFormFilled(Long id) {
        Integer answerCount = repository.getUnansweredCount(id, Question.TypeQ.Form);
        Integer questionCount = repository.getCount(Question.TypeQ.Form);
        if (questionCount.equals(answerCount)) {
            return 0;
        } else if (answerCount.equals(0)) {
            return 2;
        } else {
            return 1;
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public int statusTestFilled(Long id) {
        Integer answerCount = repository.getUnansweredCount(id, Question.TypeQ.Test);
        Integer questionCount = repository.getCount(Question.TypeQ.Test);
        if (questionCount.equals(answerCount)) {
            return 0;
        } else if (answerCount.equals(0)) {
            return 2;
        } else {
            return 1;
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public QuestionHobbyResponse getAllHobby(Long id) {
        QuestionHobbyResponse questionHobbyResponse = new QuestionHobbyResponse();
        List<QuestionHobby> questionHobbyList = questionHobbyRepository.getAllByProfile(id);
        questionHobbyResponse.setQuestions(
                questionHobbyList
                        .stream()
                        .map(qh -> questionHobbyMapper.questionHobbyToDto(qh))
                        .collect(Collectors.toList()));
        questionHobbyResponse.setMaxAnswersCount(20);
        return questionHobbyResponse;
    }

    // для быстрой заливки вопросов в бд
    @Transactional
    public void saveQuestions(List<QuestionDto> questionDtoList) {
        questionDtoList.forEach(questionDto -> {
            Question q = questionMapper.dtoToQuestion(questionDto);
            q.setId(null);
            Set<Answer> answers = q.getAnswers();
            q.setAnswers(null);
            repository.saveAndFlush(q);
            answers.forEach(answer -> {
                answer.setId(null);
                answer.setQuestion(q);
                Set<AnswerRange> answerRanges = answer.getAnswerRanges();
                answer.setAnswerRanges(null);
                answerRepository.saveAndFlush(answer);
                if (answerRanges.size() > 0) {
                    Iterator<AnswerRange> i = answerRanges.iterator();
                    AnswerRange ar = i.next();
                    ar.setId(null);
                    ar.setAnswer(answer);
                    answerRangeRepository.saveAndFlush(ar);
                }
            });
        });
//        saveMatches(7, 9);
//        saveMatches(12, 13);
//        saveMatches(19, 20);
    }

    private void saveMatches(Integer position, Integer matchPosition) {
        List<Answer> answer = answerRepository.findAllByQuestionPositionAndQuestion_TypeQOrderByIdAsc(position, Question.TypeQ.Form);
        List<Answer> matchAnswer = answerRepository.findAllByQuestionPositionAndQuestion_TypeQOrderByIdAsc(matchPosition, Question.TypeQ.Form);
        if (answer.size() == matchAnswer.size()) {
            for (int i = 0; i < answer.size(); ++i) {
                AnswerMatch answerMatch = new AnswerMatch(answer.get(i), matchAnswer.get(i));
                answerMatchRepository.save(answerMatch);
            }
        }
    }

    @Transactional
    public void saveHobby(QuestionHobbyResponse questionHobbyResponse) {
        questionHobbyResponse.getQuestions().forEach(questionHobbyDto -> {
            QuestionHobby questionHobby = questionHobbyMapper.dtoToQuestionHobby(questionHobbyDto);
            questionHobby.setId(null);
            List<AnswerHobby> answerHobbies = questionHobby.getAnswers();
            questionHobby.setAnswers(null);
            questionHobbyRepository.save(questionHobby);
            answerHobbies.forEach(answerHobby -> {
                answerHobby.setId(null);
                answerHobby.setQuestionHobby(questionHobby);
                answerHobbyRepository.save(answerHobby);
            });
        });
    }

}
