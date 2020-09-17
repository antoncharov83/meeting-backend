package pro.profsoft.meetnetbackend.model;


import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProfileAsap {
    @EmbeddedId
    private ProfileAsapPK profileAsapPK = new ProfileAsapPK();
    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private DateTime dateMessage = new DateTime();
    @MapsId("senderId")
    @ManyToOne
    private Profile senderProfile;
    @MapsId("targetId")
    @ManyToOne
    private Profile targetProfile;

    public ProfileAsap() {
    }

    public ProfileAsap(Profile senderProfile, Profile targetProfile) {
        this.senderProfile = senderProfile;
        this.targetProfile = targetProfile;
    }

    public ProfileAsapPK getProfileAsapPK() {
        return profileAsapPK;
    }

    public void setProfileAsapPK(ProfileAsapPK profileAsapPK) {
        this.profileAsapPK = profileAsapPK;
    }

    public DateTime getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(DateTime dateMessage) {
        this.dateMessage = dateMessage;
    }

    public Profile getSenderProfile() {
        return senderProfile;
    }

    public void setSenderProfile(Profile senderProfile) {
        this.senderProfile = senderProfile;
    }

    public Profile getTargetProfile() {
        return targetProfile;
    }

    public void setTargetProfile(Profile targetProfile) {
        this.targetProfile = targetProfile;
    }
}

@Embeddable
class ProfileAsapPK implements Serializable {
    private Long senderId;
    private Long targetId;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}