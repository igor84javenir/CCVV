//package fr.asigroup.ccvv.entity;
//
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//
//@Entity
//@Table(name="rdvs", indexes = {
//        @Index(columnList = "idReason"),
//        @Index(columnList = "idCity"),
//        @Index(columnList = "name"),
//        @Index(columnList = "firstName"),
//        @Index(columnList = "name, firstName"),
//        @Index(columnList = "email"),
//        @Index(columnList = "dateAndTime"),
//        @Index(columnList = "status"),
//})
//public class Rdv {
//    public enum Status{
//        Actif, Annulé, Passé
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne()
//    @JoinColumn(name = "idReason", nullable = false)
//    private ReasonRdv reasonRdv;
//
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "idCity", nullable = false)
//    private City city;
//
//    @Column(nullable = false,length = 70)
//    private String name;
//
//    @Column(nullable = false,length = 70)
//    private String firstName;
//
//    @Column(nullable = false,length = 70)
//    private String phone;
//
//    @Column(nullable = true)
//    private String email;
//
//    @Column(nullable = false)
//    private LocalDateTime dateAndTime;
//
//    @Column(name="exist")
//    private boolean exist = true;
//
//    @Column(nullable = false)
//    private int pathDuration;
//
//   /* @ManyToOne()
//    @JoinColumn(name = "idCreatedBy")
//    private User createdBy;*/
//
//    @Column(name="creatd_by")
//    private String createdBy;
//
//    @Column(nullable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime createdAt;
//
//   /* @ManyToOne()
//    @JoinColumn(name = "idModifiedBy", nullable = true)
//    private User modifiedBy;*/
//
//    @Column(name="modified_by", nullable = true)
//    private String modifiedBy;
//
//    @Column(nullable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//    private LocalDateTime modifiedAt;
//
//    @Column(nullable = false)
//    private Status status;
//
//
///*    @Column(nullable = false,name="user_creation",columnDefinition = "VARCHAR(20) DEFAULT 'root'")
//    private String utilisateurCreation;
//    @Column(nullable = false,name="date_creation",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDate dateCreation;
//    @Column(nullable = false,name="user_maj",length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'root'")
//    private String utilisateurMAJ;
//    @Column(nullable = false,name="date_maj", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//    private LocalDate dateMAJ;*/
//
//
//    public Rdv() {
//    }
//
//    public Rdv(ReasonRdv reasonRdv, City city, String name, String firstName, String phone, String email, LocalDateTime dateAndTime, boolean exist, int pathDuration, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt, Status status) {
//        this.reasonRdv = reasonRdv;
//        this.city = city;
//        this.name = name;
//        this.firstName = firstName;
//        this.phone = phone;
//        this.email = email;
//        this.dateAndTime = dateAndTime;
//        this.exist = exist;
//        this.pathDuration = pathDuration;
//        this.createdBy = createdBy;
//        this.createdAt = createdAt;
//        this.modifiedBy = modifiedBy;
//        this.modifiedAt = modifiedAt;
//        this.status = status;
//    }
//
//    public Rdv(Long id, ReasonRdv reasonRdv, City city, String name, String firstName, String phone, String email, LocalDateTime dateAndTime, boolean exist, int pathDuration, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt, Status status) {
//        this.id = id;
//        this.reasonRdv = reasonRdv;
//        this.city = city;
//        this.name = name;
//        this.firstName = firstName;
//        this.phone = phone;
//        this.email = email;
//        this.dateAndTime = dateAndTime;
//        this.exist = exist;
//        this.pathDuration = pathDuration;
//        this.createdBy = createdBy;
//        this.createdAt = createdAt;
//        this.modifiedBy = modifiedBy;
//        this.modifiedAt = modifiedAt;
//        this.status = status;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public ReasonRdv getReasonRdv() {
//        return reasonRdv;
//    }
//
//    public void setReasonRdv(ReasonRdv reasonRdv) {
//        this.reasonRdv = reasonRdv;
//    }
//
//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public LocalDateTime getDateAndTime() {
//        return dateAndTime;
//    }
//
//    public void setDateAndTime(LocalDateTime dateAndTime) {
//        this.dateAndTime = dateAndTime;
//    }
//
//    public boolean isExist() {
//        return exist;
//    }
//
//    public void setExist(boolean exist) {
//        this.exist = exist;
//    }
//
//    public int getPathDuration() {
//        return pathDuration;
//    }
//
//    public void setPathDuration(int pathDuration) {
//        this.pathDuration = pathDuration;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getModifiedBy() {
//        return modifiedBy;
//    }
//
//    public void setModifiedBy(String modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }
//
//    public LocalDateTime getModifiedAt() {
//        return modifiedAt;
//    }
//
//    public void setModifiedAt(LocalDateTime modifiedAt) {
//        this.modifiedAt = modifiedAt;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return "Rdv{" +
//                "id=" + id +
//                ", reasonRdv=" + reasonRdv +
//                ", city=" + city +
//                ", name='" + name + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", phone='" + phone + '\'' +
//                ", email='" + email + '\'' +
//                ", dateAndTime=" + dateAndTime +
//                ", exist=" + exist +
//                ", pathDuration=" + pathDuration +
//                ", createdBy='" + createdBy + '\'' +
//                ", createdAt=" + createdAt +
//                ", modifiedBy='" + modifiedBy + '\'' +
//                ", modifiedAt=" + modifiedAt +
//                ", status=" + status +
//                '}';
//    }
//}
//
