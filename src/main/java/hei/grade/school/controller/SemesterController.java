package hei.grade.school.controller;

import hei.grade.school.model.Semester;
import hei.grade.school.service.SemesterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/semesters")
public class SemesterController {

    public SemesterService semesterService;

    @GetMapping()
    public List<Semester> getAllSemester(){
        return semesterService.findAllSemester();
    }

    @GetMapping("/{semester_id}")
    public Semester getSemesterById(@PathVariable String semester_id){
        return semesterService.findSemesterById(semester_id);
    }

    @PostMapping()
    public Semester createSemester(
            @RequestBody Semester semester
    ){
        return semesterService.addSemester(semester);
    }

    @PutMapping("/{semester_id}")
    public Semester updateSemester(
            @PathVariable String semester_id,
            @RequestBody Semester semester
    ){
        return semesterService.updateSemesterById(semester_id, semester);
    }

    @DeleteMapping("/{semester_id}")
    public String deleteSemesterById(@PathVariable String semester_id){
         return semesterService.deleteSemesterById(semester_id);
    }

}
