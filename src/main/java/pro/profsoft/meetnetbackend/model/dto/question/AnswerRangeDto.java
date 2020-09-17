package pro.profsoft.meetnetbackend.model.dto.question;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerRangeDto {
    @JsonProperty("minRange")
    private Integer minRange;
    @JsonProperty("maxRange")
    private Integer maxRange;
    @JsonProperty("minCurrentRange")
    private Integer minCurrentRange;
    @JsonProperty("maxCurrentRange")
    private Integer maxCurrentRange;

    public Integer getMinRange() {
        return minRange;
    }

    public void setMinRange(Integer minRange) {
        this.minRange = minRange;
    }

    public Integer getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
    }

    public Integer getMinCurrentRange() {
        return minCurrentRange;
    }

    public void setMinCurrentRange(Integer minCurrentRange) {
        this.minCurrentRange = minCurrentRange;
    }

    public Integer getMaxCurrentRange() {
        return maxCurrentRange;
    }

    public void setMaxCurrentRange(Integer maxCurrentRange) {
        this.maxCurrentRange = maxCurrentRange;
    }
}
