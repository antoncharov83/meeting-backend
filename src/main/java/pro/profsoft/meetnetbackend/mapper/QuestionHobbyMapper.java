package pro.profsoft.meetnetbackend.mapper;

import org.mapstruct.Mapper;
import pro.profsoft.meetnetbackend.model.QuestionHobby;
import pro.profsoft.meetnetbackend.model.dto.hobby.QuestionHobbyDto;

@Mapper(componentModel = "spring")
public interface QuestionHobbyMapper {
    QuestionHobbyDto questionHobbyToDto(QuestionHobby questionHobby);
    QuestionHobby dtoToQuestionHobby(QuestionHobbyDto questionHobbyDto);
}
