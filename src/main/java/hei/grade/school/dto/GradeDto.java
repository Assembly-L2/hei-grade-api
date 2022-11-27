package hei.grade.school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GradeDto {

    private Float mark;

    private String user_id;

    private String id_evaluation;
}
