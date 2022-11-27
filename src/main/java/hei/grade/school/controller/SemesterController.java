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

    @GetMapping("/{id_semester}")
    public Semester getSemesterById(@PathVariable String id_semester){
        return semesterService.findSemesterById(id_semester);
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

    @DeleteMapping("/{id_semester}")
    public String deleteSemesterById(@PathVariable String id_semester){
         return semesterService.deleteSemesterById(id_semester);
    }

}
