package fr.asigroup.ccvv.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name="rdvs", indexes = {
        @Index(columnList = "rdv_reason_id"),
        @Index(columnList = "city_id"),
        @Index(columnList = "name"),
        @Index(columnList = "first_name"),
        @Index(columnList = "name, first_name"),
        @Index(columnList = "mail"),
        @Index(columnList = "status"),
})
public class Rdv {
    private static final int CREATION_MAIL_TIME = 10;

    public enum Status{
        Actif, Passé, Annulé
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "mail")
    private String mail;

    @Column(name = "no_mail", nullable = false)
    private boolean noMail = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

//    @Column(name="exist")
//    private boolean exist = true;

    @Column(name = "status",  nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "rdv_reason_id", nullable = false)
    private ReasonRdv reasonRdv;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_at",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    public Rdv() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReasonRdv getReasonRdv() {
        return reasonRdv;
    }

    public void setReasonRdv(ReasonRdv reasonRdv) {
        this.reasonRdv = reasonRdv;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

//    public boolean isExist() {
//        return exist;
//    }

//    public void setExist(boolean exist) {
//        this.exist = exist;
//    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isNoMail() {
        return noMail;
    }

    public void setNoMail(boolean noMail) {
        this.noMail = noMail;
    }

    public int getRdvDuration() {
        if (!noMail) {
            return reasonRdv.getDurationMinutes();
        } else {
            return reasonRdv.getDurationMinutes() + CREATION_MAIL_TIME;
        }
    }


    @Override
    public String toString() {
        return "Rdv{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", noMail=" + noMail +
                ", date=" + date +
                ", time=" + time +
                ", status=" + status +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}

