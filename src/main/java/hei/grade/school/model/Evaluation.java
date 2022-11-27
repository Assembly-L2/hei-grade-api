package hei.grade.school.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Evaluation {
    @Id
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();
    private LocalDate dateExam;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_Semester")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "id_Course")
    private Course course;

    public Evaluation(LocalDate dateExam, Boolean status, Semester semester, Course course){
        this.dateExam=dateExam;
        this.status=status;
        this.semester=semester;
    }
}
