package pro.profsoft.meetnetbackend.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Relationship {
    @EmbeddedId
    private CompositePK id = new CompositePK();
    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private DateTime dateDislike = new DateTime();
    @MapsId("ownerId")
    @ManyToOne
    private Profile ownerProfile;
    @MapsId("targetId")
    @ManyToOne
    private Profile dislikeProfile;

    public Relationship() {
    }

    public Relationship(Profile ownerProfile, Profile dislikeProfile) {
        this.ownerProfile = ownerProfile;
        this.dislikeProfile = dislikeProfile;
    }

    public CompositePK getId() {
        return id;
    }

    public void setId(CompositePK id) {
        this.id = id;
    }

    public Profile getOwnerProfile() {
        return ownerProfile;
    }

    public void setOwnerProfile(Profile ownerProfile) {
        this.ownerProfile = ownerProfile;
    }

    public Profile getDislikeProfile() {
        return dislikeProfile;
    }

    public void setDislikeProfile(Profile dislikeProfile) {
        this.dislikeProfile = dislikeProfile;
    }

    public DateTime getDateDislike() {
        return dateDislike;
    }

    public void setDateDislike(DateTime dateDislike) {
        this.dateDislike = dateDislike;
    }
}

@Embeddable
class CompositePK implements Serializable {
    private Long ownerId;
    private Long targetId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}

