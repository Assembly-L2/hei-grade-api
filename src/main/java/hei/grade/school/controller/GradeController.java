package hei.grade.school.controller;

import hei.grade.school.dto.GradeDto;
import hei.grade.school.model.Grade;
import hei.grade.school.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/grades")
public class GradeController {

    private GradeService gradeService;

    @GetMapping()
    public List<Grade> getAllGrades(){ return gradeService.getAllGrades(); }

    @GetMapping("/{grade_id}")
    public Grade getGradeById(@PathVariable String grade_id){ return gradeService.getGradeBYId(grade_id); }

    @PostMapping()
    public Grade createGrade(@RequestBody GradeDto gradeDto){ return gradeService.createGrade(gradeDto); }

    @PutMapping("/{grade_id}")
    public Grade updateGrade(
            @PathVariable String grade_id,
            @RequestBody GradeDto gradeDto
    ){ return gradeService.updateGrade(grade_id, gradeDto); }

    @DeleteMapping("/{grade_id}")
    public String deleteGrade(@PathVariable String grade_id){ return gradeService.deleteGradeById(grade_id); }

}
