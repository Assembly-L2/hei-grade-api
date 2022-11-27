package hei.grade.school.mapper;

import hei.grade.school.model.Evaluation;
import hei.grade.school.model.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
@Setter
public class GradeMapper {

    private Float mark;

    private String user_id;

    private String id_evaluation;
}
