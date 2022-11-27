package hei.grade.school.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class EvaluationMapper {

    private LocalDate dateExam;

    private Boolean status;

    private String semester_id;

    private String course_id;
}
