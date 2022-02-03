package fr.asigroup.ccvv.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "unavailable_days")
public class EntityUnavailableDays {

    public enum Dispo{
        DISPONIBLE, INDISPONIBLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private LocalDate date;


   /*@ManyToOne()
    @JoinColumn(name = "created_by")
    private User createdBy;*/
    @Column(name = "created_by")
    private String createdBy;

    @Column(name="created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /*@ManyToOne()
    @JoinColumn(name = "modified_by", nullable = true)
    private User modifiedBy;*/
    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_at",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    @Column(name = "dispo",  nullable = false)
    private Dispo dispo;



    public EntityUnavailableDays() {
    }

    public EntityUnavailableDays(LocalDate date, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt, Dispo dispo) {
        this.date = date;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
        this.dispo = dispo;
    }

    public EntityUnavailableDays(Long id, LocalDate date, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt, Dispo dispo) {
        this.id = id;
        this.date = date;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
        this.dispo = dispo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Dispo getDispo() {
        return dispo;
    }

    public void setDispo(Dispo dispo) {
        this.dispo = dispo;
    }

    @Override
    public String toString() {
        return "EntityUnavailableDays{" +
                "id=" + id +
                ", date=" + date +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedAt=" + modifiedAt +
                ", dispo=" + dispo +
                '}';
    }
}
