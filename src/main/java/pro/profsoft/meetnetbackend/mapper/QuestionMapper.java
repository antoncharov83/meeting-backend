package pro.profsoft.meetnetbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pro.profsoft.meetnetbackend.model.Answer;
import pro.profsoft.meetnetbackend.model.AnswerRange;
import pro.profsoft.meetnetbackend.model.Question;
import pro.profsoft.meetnetbackend.model.dto.question.AnswerDto;
import pro.profsoft.meetnetbackend.model.dto.question.AnswerRangeDto;
import pro.profsoft.meetnetbackend.model.dto.question.QuestionDto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(source = "answers", target = "answers")
    QuestionDto questionToQuestionDto(Question q);
    @Mapping(source = "answerRanges", target = "answerRanges", qualifiedByName = "answerRangesToDto")
    AnswerDto answerToDto(Answer answer);

    @Named("answerRangesToDto")
    static AnswerRangeDto answerRangesToDto(Set<AnswerRange> answerRangeSet) {
        AnswerRangeDto answerRangeDto = new AnswerRangeDto();
        if (answerRangeSet.size() > 0) {
            Iterator<AnswerRange> i = answerRangeSet.iterator();
            AnswerRange answerRange = i.next();
            answerRangeDto.setMinRange(answerRange.getMinRange());
            answerRangeDto.setMinCurrentRange(answerRange.getMinCurrentRange());
            answerRangeDto.setMaxRange(answerRange.getMaxRange());
            answerRangeDto.setMaxCurrentRange(answerRange.getMaxCurrentRange());
            return answerRangeDto;
        }
        else
            return null;
    }

    @Mapping(source = "answers", target = "answers")
    Question dtoToQuestion(QuestionDto q);
    @Mapping(source = "answerRanges", target = "answerRanges", qualifiedByName = "dtoToAnswerRanges")
    Answer dtoToAnswer(AnswerDto a);

    @Named("dtoToAnswerRanges")
    static Set<AnswerRange> dtoToAnswerRanges(AnswerRangeDto answerRangeDto) {
        if (answerRangeDto !=null) {
            AnswerRange answerRange = new AnswerRange();
            answerRange.setMinRange(answerRangeDto.getMinRange());
            answerRange.setMinCurrentRange(answerRangeDto.getMinCurrentRange());
            answerRange.setMaxRange(answerRangeDto.getMaxRange());
            answerRange.setMaxCurrentRange(answerRangeDto.getMaxCurrentRange());
            Set<AnswerRange> answerRanges = new HashSet<>();
            answerRanges.add(answerRange);
            return answerRanges;
        } else
            return new HashSet<>();
    }
}
