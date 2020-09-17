package pro.profsoft.meetnetbackend.model;

import javax.persistence.*;

@Entity
public class ProfileReport extends BaseEntity {
    public enum TypeReport { UNACCEPTABLE_CONTENT, FAILURE_CONTENT, UNMATCHABLE_CONTENT } // Причина
    private Long imgId;
    @Enumerated(EnumType.ORDINAL)
    private TypeReport typeReport;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public ProfileReport() {
    }

    public ProfileReport(Profile profile, Long imgId, TypeReport typeReport) {
        this.imgId = imgId;
        this.typeReport = typeReport;
        this.profile = profile;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public TypeReport getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(TypeReport typeReport) {
        this.typeReport = typeReport;
    }
}
