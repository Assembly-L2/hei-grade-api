package hei.grade.school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class EvaluationDto {

    private LocalDate dateExam;

    private Boolean status;

    private String semester_id;

    private String course_id;
}
