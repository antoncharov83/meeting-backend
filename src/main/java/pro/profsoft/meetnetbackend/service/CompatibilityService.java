package pro.profsoft.meetnetbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.profsoft.meetnetbackend.exception.EntityNotFoundException;
import pro.profsoft.meetnetbackend.model.Profile;
import pro.profsoft.meetnetbackend.model.ProfileAnswer;
import pro.profsoft.meetnetbackend.model.Question;
import pro.profsoft.meetnetbackend.repository.CompatibilityRepository;
import pro.profsoft.meetnetbackend.repository.ProfileRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CompatibilityService {
    private CompatibilityRepository compatibilityRepository;
    private ProfileRepository profileRepository;

    public CompatibilityService(CompatibilityRepository compatibilityRepository,
                                ProfileRepository profileRepository) {
        this.compatibilityRepository = compatibilityRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public Profile getProfile(Long Id) {
        return profileRepository.getAllWithProfileAnswers(Id)
                .orElseThrow(() -> new EntityNotFoundException(Id + " profile not found"));
    }

    @Transactional
    public Optional<ProfileAnswer> getAnswer(Long Id, Integer position, Question.TypeQ typeQ) {
        Optional<ProfileAnswer> answer = getProfile(Id)
                .getProfileAnswers().stream()
                .filter(pa -> pa.getQuestion().getPosition().equals(position) && pa.getQuestion().getTypeQ().equals(typeQ))
                .findFirst();
        return answer;
    }

    @Transactional
    public List<ProfileAnswer> getAnswers(Long Id, Integer position, Question.TypeQ typeQ) {
        return getProfile(Id).getProfileAnswers()
                .stream()
                .filter(pa -> pa.getQuestion().getPosition().equals(position) && pa.getQuestion().getTypeQ().equals(typeQ))
                .collect(Collectors.toList());
    }

    // разница в росте не более 9%
    public Double calculate0Question(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 6, Question.TypeQ.Form);
        Optional<ProfileAnswer> answer2 = getAnswer(secondId, 6, Question.TypeQ.Form);
        if (answer1.isPresent() && answer2.isPresent()) {
            Double percent = answer1.get().getMinCurrentRange() > answer2.get().getMinCurrentRange() ?
                    (answer2.get().getMinCurrentRange().doubleValue() / answer1.get().getMinCurrentRange().doubleValue()) * 100 :
                    (answer1.get().getMinCurrentRange().doubleValue() / answer2.get().getMinCurrentRange().doubleValue()) * 100;
            if (percent <= 9) {
                return 100.0;
            }
        }
        return 0.0;
    }

    public Double calculate1Question(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 2, Question.TypeQ.Test);
        Optional<ProfileAnswer> answer2 = getAnswer(secondId, 2, Question.TypeQ.Test);
        if (answer1.isPresent() && answer2.isPresent()) {
            if (answer1.get().getAnswer().getId().equals(answer2.get().getAnswer().getId())) {
                return 100.0;
            } else if (Math.abs(answer1.get().getAnswer().getId() - answer2.get().getAnswer().getId()) == 1) {
                return 50.0;
            } else {
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }

    // Вопросы со 2 по 8-ый либо 100 либо 0
    public Double calculateBinaryQuestion(Long firstId, Long secondId, Integer position) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, position, Question.TypeQ.Test);
        Optional<ProfileAnswer> answer2 = getAnswer(secondId, position, Question.TypeQ.Test);
        if (answer1.isPresent() && answer2.isPresent()) {
            return answer1.get().getAnswer().getId().equals(answer2.get().getAnswer().getId()) ? 100.0 : 0.0;
        } else {
            return 0.0;
        }
    }

    public Double calculate9Question(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 3, Question.TypeQ.Test);
        if (answer1.isPresent()) {
            if (answer1.get().getAnswer().getText().equalsIgnoreCase("Мужчина")) {
                answer1 = getAnswer(firstId, 21, Question.TypeQ.Test);
            } else {
                answer1 = getAnswer(firstId, 22, Question.TypeQ.Test);
            }
        } else {
            return 0.0;
        }
        Optional<ProfileAnswer> answer2 = getAnswer(secondId, 3, Question.TypeQ.Test);
        if (answer2.isPresent()) {
            if (answer2.get().getAnswer().getText().equalsIgnoreCase("Мужчина")) {
                answer2 = getAnswer(firstId, 21, Question.TypeQ.Test);
            } else {
                answer2 = getAnswer(firstId, 22, Question.TypeQ.Test);
            }
        } else {
            return 0.0;
        }
        if (answer1.isPresent() && answer2.isPresent()) {
            return Math.abs(answer1.get().getAnswer().getId() - answer2.get().getAnswer().getId()) == 3 ? 100.0 : 0.0;
        } else {
            return 0.0;
        }
    }

    public Double calculate10Question(Long firstId, Long secondId) {
        List<ProfileAnswer> answers1 = getAnswers(firstId, 10, Question.TypeQ.Test);
        List<ProfileAnswer> answers2 = getAnswers(secondId, 10, Question.TypeQ.Test);
        AtomicInteger numEqualAnswer = new AtomicInteger(0);
        answers1.forEach(a1 -> answers2
                .forEach(a2 -> {
                    if (a2.getAnswer().getId().equals(a1.getAnswer().getId())) {
                    numEqualAnswer.getAndIncrement();
                    }
                }));
        Double percent = (double)numEqualAnswer.get() / answers1.size();
        if (percent >= 0.5) {
            return 100.0;
        } else {
            switch (numEqualAnswer.get()) {
                case 1: return 20.0;
                case 2: return 40.0;
                case 3: return 60.0;
                case 4: return 80.0;
                default: return 0.0;
            }
        }
    }

    public Double calculate11Question(Long firstId, Long secondId) {
        String firstSymbol = getSorted(firstId);
        String secondSymbol = getSorted(secondId);
        if (firstSymbol == null || secondSymbol == null) {
            return 0.0;
        }
        if ((firstSymbol.equals("S") && (secondSymbol.equals("F") || secondSymbol.equals("M"))) ||
                (secondSymbol.equals("S") && (firstSymbol.equals("F") || firstSymbol.equals("M")))) {
            return 100.0;
        }
        if ((firstSymbol.equals("H") && (secondSymbol.equals("F") || secondSymbol.equals("M"))) ||
                secondSymbol.equals("H") && (firstSymbol.equals("F") || firstSymbol.equals("M"))) {
            return 100.0;
        }
        if ((firstSymbol.equals("S") && secondSymbol.equals("H")) ||
                (secondSymbol.equals("S") && firstSymbol.equals("H"))) {
            return 30.0;
        }
        if ((firstSymbol.equals("M") && secondSymbol.equals("F")) ||
                (secondSymbol.equals("M") && firstSymbol.equals("F"))) {
            return 30.0;
        }
        return 0.0;
    }

    private String getSorted(Long id) {
        Map<String, Integer> map = new HashMap<>();
        Optional<ProfileAnswer> answer = getAnswer(id, 12, Question.TypeQ.Test);
        answer.ifPresent(a -> map.put("H", a.getMaxCurrentRange() == null ? 0 : a.getMaxCurrentRange()));
        answer = getAnswer(id, 17, Question.TypeQ.Test);
        answer.ifPresent(a -> map.put("H", a.getMaxCurrentRange() == null ? map.get("H") : map.get("H") + a.getMaxCurrentRange()));
        answer = getAnswer(id, 13, Question.TypeQ.Test);
        answer.ifPresent(a -> map.put("S", a.getMaxCurrentRange() == null ? 0 : a.getMaxCurrentRange()));
        answer = getAnswer(id, 18, Question.TypeQ.Test);
        answer.ifPresent(a -> map.put("S", a.getMaxCurrentRange() == null ? map.get("S") : map.get("S") + a.getMaxCurrentRange()));
        answer = getAnswer(id, 14, Question.TypeQ.Test);
        answer.ifPresent(a -> map.put("F", a.getMaxCurrentRange() == null ? 0 : a.getMaxCurrentRange()));
        answer = getAnswer(id, 19, Question.TypeQ.Test);
        answer.ifPresent(a -> map.put("F", a.getMaxCurrentRange() == null ? map.get("F") : map.get("F") + a.getMaxCurrentRange()));
        answer = getAnswer(id, 15, Question.TypeQ.Test);
        answer.ifPresent(a -> map.put("M", a.getMaxCurrentRange() == null ? 0 : a.getMaxCurrentRange()));
        answer = getAnswer(id, 20, Question.TypeQ.Test);
        answer.ifPresent(a -> map.put("M", a.getMaxCurrentRange() == null ? map.get("M") : map.get("M") + a.getMaxCurrentRange()));
        Iterator<Map.Entry<String, Integer>> i = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new))
                .entrySet()
                .iterator();
        if (i.hasNext()) {
            return i.next().getKey();
        }
        return null;
    }

    public Integer calculateCompability(Long firstId, Long secondId) {
        Double first = calculate1Question(firstId, secondId) + calculateBinaryQuestion(firstId, secondId, 2);
        first += Arrays.asList(1,2,3,4,5,6,7,8).stream()
                .mapToDouble(i -> calculateBinaryQuestion(firstId, secondId, i)).sum();
        first = (first / 800.0) / 4;
        Double second = calculate9Question(firstId, secondId) / 4;
        Double third = calculate10Question(firstId, secondId) / 4;
        Double fourth = calculate11Question(firstId, secondId) / 4;
        Double res = first + second + third + fourth;
        return res.intValue();
    }

    // Предпочтение по возрасту
    public Integer formCompatibility1(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 5, Question.TypeQ.Form);
        if (answer1.isPresent()) {
            Profile another = profileRepository.findById(secondId)
                    .orElseThrow(() -> new EntityNotFoundException(secondId + " profile not found"));
            if (another.getAge() != null && another.getAge() >= answer1.get().getMinCurrentRange() && another.getAge() <= answer1.get().getMaxCurrentRange()) {
                return 100;
            }
        }
        return 0;
    }

    // Предпочтение по росту
    public Integer formCompatibility2(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 8, Question.TypeQ.Form);
        if (answer1.isPresent()) {
            Optional<ProfileAnswer> answer2 = getAnswer(secondId, 6, Question.TypeQ.Form);
            if (answer2.isPresent()) {
                if (answer2.get().getMaxCurrentRange() >= answer1.get().getMinCurrentRange() &&
                        answer2.get().getMaxCurrentRange() <= answer1.get().getMaxCurrentRange()) {
                    return 100;
                }
            }
        }
        return 0;
    }

    // Предпочтение по телосложению
    public Integer formCompatibility3(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 9, Question.TypeQ.Form);
        if (answer1.isPresent()) {
            Optional<ProfileAnswer> answer2 = getAnswer(secondId, 7, Question.TypeQ.Form);
            if (answer2.isPresent()) {
                return answer1.get().getAnswer().getText().equalsIgnoreCase(answer1.get().getAnswer().getText()) ? 100 : 0;
            }
        }
        return 0;
    }

    // Предпочтение по образованию
    public Integer formCompatibility4(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 13, Question.TypeQ.Form);
        if (answer1.isPresent()) {
            Optional<ProfileAnswer> answer2 = getAnswer(secondId, 12, Question.TypeQ.Form);
            if (answer2.isPresent()) {
                return answer1.get().getAnswer().getText().equalsIgnoreCase(answer1.get().getAnswer().getText()) ? 100 : 0;
            }
        }
        return 0;
    }

    // Предпочтение по уровню дохода
    public Integer formCompatibility5(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 16, Question.TypeQ.Form);
        if (answer1.isPresent()) {
            Optional<ProfileAnswer> answer2 = getAnswer(secondId, 15, Question.TypeQ.Form);
            if (answer2.isPresent()) {
                if (answer2.get().getMaxCurrentRange() >= answer1.get().getMinCurrentRange() &&
                        answer2.get().getMaxCurrentRange() <= answer1.get().getMaxCurrentRange()) {
                    return 100;
                }
            }
        }
        return 0;
    }

    // Предпочтение по жилью
    public Integer formCompatibility6(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 20, Question.TypeQ.Form);
        if (answer1.isPresent()) {
            Optional<ProfileAnswer> answer2 = getAnswer(secondId, 19, Question.TypeQ.Form);
            if (answer2.isPresent()) {
                return answer1.get().getAnswer().getText().equalsIgnoreCase(answer1.get().getAnswer().getText()) ? 100 : 0;
            }
        }
        return 0;
    }

    // Предпочтение по курению
    public Integer formCompatibility7(Long firstId, Long secondId) {
        Optional<ProfileAnswer> answer1 = getAnswer(firstId, 22, Question.TypeQ.Form);
        if (answer1.isPresent()) {
            Optional<ProfileAnswer> answer2 = getAnswer(secondId, 21, Question.TypeQ.Form);
            if (answer2.isPresent()) {
                if (answer1.get().getAnswer().getText().equalsIgnoreCase("Да")) {
                    return 100;
                }
                if (answer1.get().getAnswer().getText().equalsIgnoreCase("Нет") &&
                        answer2.get().getAnswer().getText().equalsIgnoreCase("Категорически не приемлю")) {
                    return 100;
                }
            }
        }
        return 0;
    }

    public Integer formCompatibilityAll(Long firstId, Long secondId) {
        return  (formCompatibility1(firstId, secondId) +
                formCompatibility2(firstId, secondId) +
                formCompatibility3(firstId, secondId) +
                formCompatibility4(firstId, secondId) +
                formCompatibility5(firstId, secondId) +
                formCompatibility6(firstId, secondId) +
                formCompatibility7(firstId, secondId)) / 7;
    }
}
