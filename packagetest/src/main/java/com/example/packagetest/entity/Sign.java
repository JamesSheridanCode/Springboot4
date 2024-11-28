package com.example.packagetest.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="sign")
public class Sign {

    public Sign() {

    }

    public Sign(String name, String comment, Long petitionId) {

        this.petitionId = petitionId;
        this.signerComment = comment;
        this.signerName = name;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long signId; // Primary key

    @Column(nullable = false, length = 100)
    private String signerName; // Name of the user signing the petition

    @Column(nullable = false, length = 255)
    private String signerComment; // User's comment

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date();

    @Column(nullable = false, updatable = false)
    private Long petitionId; // Petition ID as a foreign key (no @ManyToOne)

    @Override
    public String toString() {
        return "Sign{" +
                "signId=" + signId +
                ", signerName='" + signerName + '\'' +
                ", signerComment='" + signerComment + '\'' +
                ", createdAt=" + createdAt +
                ", petitionId=" + petitionId +
                '}';
    }

    // Getters and Setters
    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public String getSignerComment() {
        return signerComment;
    }

    public void setSignerComment(String signerComment) {
        this.signerComment = signerComment;
    }

    public Long getPetitionId() {
        return petitionId;
    }

    public void setPetitionId(Long petitionId) {
        this.petitionId = petitionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

