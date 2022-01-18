package fr.asigroup.ccvv.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reasons_rdv",indexes={@Index(name="category",columnList="category"),@Index(name="name",columnList="name")})
public class ReasonRdv {

    public  enum ReasonRdvCategorie {
        ANTS, Prefecture
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false,length=80)
    private String name;

    @Column(name="category",nullable=false)
    private ReasonRdvCategorie category;


    @Column(name="duration_minutes",nullable = false)
    private int durationMinutes ;

    @Column(name="link_doc",nullable = false,length=255)
    private String linkDoc;

    @Column(name = "created_by",nullable = false,length=30,columnDefinition = "VARCHAR(30) DEFAULT 'root'")
    private String createdBy;

    @Column(name="created_at",nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "modified_by",nullable = false,length=30,columnDefinition = "VARCHAR(30) DEFAULT 'root'")
    private String modifiedBy;

    @Column(name="modified_at",nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP on update current_timestamp")
    private LocalDateTime modifiedAt;
    @Column(name = "exist")
    private boolean exist = true;

    public ReasonRdv() {
    }

    public ReasonRdv(String name, ReasonRdvCategorie category, int durationMinutes, String linkDoc, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt, boolean exist) {
        this.name = name;
        this.category = category;
        this.durationMinutes = durationMinutes;
        this.linkDoc = linkDoc;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
        this.exist = exist;
    }

    public ReasonRdv(Long id, String name, ReasonRdvCategorie category, int durationMinutes, String linkDoc, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt, boolean exist) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.durationMinutes = durationMinutes;
        this.linkDoc = linkDoc;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
        this.exist = exist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReasonRdvCategorie getCategory() {
        return category;
    }

    public void setCategory(ReasonRdvCategorie category) {
        this.category = category;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getLinkDoc() {
        return linkDoc;
    }

    public void setLinkDoc(String linkDoc) {
        this.linkDoc = linkDoc;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "ReasonRdv{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", durationMinutes=" + durationMinutes +
                ", linkDoc='" + linkDoc + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedAt=" + modifiedAt +
                ", exist=" + exist +
                '}';
    }
}
