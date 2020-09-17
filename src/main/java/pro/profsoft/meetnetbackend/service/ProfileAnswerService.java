package pro.profsoft.meetnetbackend.service;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.profsoft.meetnetbackend.exception.EntityNotFoundException;
import pro.profsoft.meetnetbackend.model.*;
import pro.profsoft.meetnetbackend.model.dto.hobby.QuestionHobbyDto;
import pro.profsoft.meetnetbackend.model.dto.question.AnswerDto;
import pro.profsoft.meetnetbackend.model.dto.question.QuestionDto;
import pro.profsoft.meetnetbackend.repository.*;

@Service
public class ProfileAnswerService {
    private ProfileAnswerRepository profileAnswerRepository;
    private ProfileRepository profileRepository;
    private QuestionRepository questionRepository;
    private QuestionHobbyRepository questionHobbyRepository;
    private AnswerRepository answerRepository;
    private AnswerHobbyRepository answerHobbyRepository;
    private ProfileHobbyAnswerRepository profileHobbyAnswerRepository;

    public ProfileAnswerService(ProfileAnswerRepository profileAnswerRepository,
                                ProfileRepository profileRepository,
                                QuestionRepository questionRepository,
                                QuestionHobbyRepository questionHobbyRepository,
                                AnswerRepository answerRepository,
                                AnswerHobbyRepository answerHobbyRepository,
                                ProfileHobbyAnswerRepository profileHobbyAnswerRepository) {
        this.profileAnswerRepository = profileAnswerRepository;
        this.profileRepository = profileRepository;
        this.questionRepository = questionRepository;
        this.questionHobbyRepository = questionHobbyRepository;
        this.answerRepository = answerRepository;
        this.answerHobbyRepository = answerHobbyRepository;
        this.profileHobbyAnswerRepository = profileHobbyAnswerRepository;
    }

//    @Transactional
//    public void save(Long profileId, Long questionId, List<Long> answerIds) {
//        Profile profile = profileRepository.findById(profileId)
//                .orElseThrow(() -> new EntityNotFoundException(profileId+" Profile not found"));
//        Question question = questionRepository.findById(questionId)
//                .orElseThrow(() -> new EntityNotFoundException(questionId+" Question not found"));
//        List<Answer> answers = answerIds.stream()
//                .map(answerId -> answerRepository
//                        .findById(answerId)
//                        .orElseThrow(() -> new EntityNotFoundException(answerId+" Answer not found")))
//                .collect(Collectors.toList());
//        answers.forEach(answer -> profileAnswerRepository
//                .saveAndFlush(new ProfileAnswer(question, profile, answer)));
//    }

    @Transactional
    public void saveAnswer(Long id, QuestionDto questionDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " Profile not found"));
        Question question = questionRepository.findById(questionDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(questionDto.getId() + " Question not found"));
        if (questionDto.getAnswers() != null) {
            questionDto.getAnswers().forEach(answerDto -> {
                if (question.getQuestionType() != null) {
                    switch (question.getQuestionType()) {
                        case 1:
                        case 2:
                        case 9:
                        case 10: // вопросы с rangeBar
                            ProfileAnswer profileAnswer = setProfileAnswer(answerDto, profile, question);
                            profileAnswerRepository.save(profileAnswer);
                            break;
                        case 11:
                        case 12:
                        case 13: // вопросы с ручным вводом
                            profileAnswer = setProfileAnswer(answerDto, profile, question);
                            profileAnswerRepository.save(profileAnswer);
                            if (question.getPosition().equals(4) && question.getTypeQ().equals(Question.TypeQ.Form)) {
                                setAge(profile, profileAnswer);
                            }
                            break;
                        default: // вопросы с выбором
                            profileAnswer = setProfileAnswer(answerDto, profile, question);
                            if (answerDto.getChecked() != null && answerDto.getChecked().equals(true)) {
                                profileAnswerRepository.save(profileAnswer);
                            } else {
                                profileAnswerRepository.delete(profileAnswer);
                            }
                    }
                }
            });
        }
    }

    private ProfileAnswer setProfileAnswer(AnswerDto answerDto, Profile profile, Question question) {
        Answer answer = answerRepository.findById(answerDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(answerDto.getId() + " Answer not found"));
        ProfileAnswer profileAnswer = profileAnswerRepository.findFirstByProfileAndAnswerAndQuestion(profile, answer, question)
                .orElse(new ProfileAnswer(question, profile, answer));
        if (answerDto.getAnswerRanges() != null) {
            profileAnswer.setMinRange(answerDto.getAnswerRanges().getMinRange());
            profileAnswer.setMinCurrentRange(answerDto.getAnswerRanges().getMinCurrentRange());
            profileAnswer.setMaxRange(answerDto.getAnswerRanges().getMaxRange());
            profileAnswer.setMaxCurrentRange(answerDto.getAnswerRanges().getMaxCurrentRange());
        }
        profileAnswer.setTextEdit(answerDto.getTextEdit());
        return profileAnswer;
    }

    private void setAge(Profile profile, ProfileAnswer profileAnswer) {
            DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("dd/MM/yyyy");
            DateTime birthday = dateStringFormat.parseDateTime(profileAnswer.getTextEdit());
            profile.setAge(Years.yearsBetween(birthday, DateTime.now()).getYears());
            profileRepository.save(profile);
    }

    @Transactional
    public void saveAnswerHobby(Long id, QuestionHobbyDto questionHobbyDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " Profile not found"));
        QuestionHobby questionHobby = questionHobbyRepository.findById(questionHobbyDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(questionHobbyDto.getId() + " Hobby question not found"));
        if (questionHobbyDto.getAnswers() != null) {
            questionHobbyDto.getAnswers().forEach(answerHobbyDto -> {
                AnswerHobby answerHobby = answerHobbyRepository.findById(answerHobbyDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException(answerHobbyDto.getId() + " Hobby answer not found"));
                ProfileHobbyAnswer profileHobbyAnswer = profileHobbyAnswerRepository
                        .findFirstByProfileAndQuestionAndAnswer(profile, questionHobby, answerHobby)
                        .orElse(new ProfileHobbyAnswer(profile, questionHobby, answerHobby));
                profileHobbyAnswer.setCheckedCan(answerHobbyDto.getCheckedCan());
                profileHobbyAnswer.setCheckedWant(answerHobbyDto.getCheckedWant());
                if (profileHobbyAnswer.getCheckedCan() || profileHobbyAnswer.getCheckedWant()) {
                    profileHobbyAnswerRepository.save(profileHobbyAnswer);
                } else {
                    profileHobbyAnswerRepository.delete(profileHobbyAnswer);
                }
            });
        }
    }
}
