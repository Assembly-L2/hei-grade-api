package hei.grade.school.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Users {
    @Id
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();

    private String firstName;
    private String lastName;
    private Character sex;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String email;
    private LocalDate entranceDatetime;
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_groupe")
    private Groupe groupe;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grade;


}