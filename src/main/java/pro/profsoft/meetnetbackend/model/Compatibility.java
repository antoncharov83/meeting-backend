package pro.profsoft.meetnetbackend.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Compatibility {
    @EmbeddedId
    private CompositePK id = new CompositePK();
    @MapsId("ownerId")
    @ManyToOne
    private Profile ownerProfile;
    @MapsId("targetId")
    @ManyToOne
    private Profile targetProfile;
    private Double compatibility;

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

    public Profile getTargetProfile() {
        return targetProfile;
    }

    public void setTargetProfile(Profile targetProfile) {
        this.targetProfile = targetProfile;
    }

    public Double getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(Double compatibility) {
        this.compatibility = compatibility;
    }
}
