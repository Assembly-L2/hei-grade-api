package hei.grade.school.service;

import hei.grade.school.mapper.GradeMapper;
import hei.grade.school.model.Grade;
import hei.grade.school.repository.EvalutionRepository;
import hei.grade.school.repository.GradeRepository;
import hei.grade.school.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {

    private GradeRepository gradeRepository;
    private UsersRepository usersRepository;
    private EvalutionRepository evalutionRepository;

    // Get all grades
    public List<Grade> getAllGrades(){ return gradeRepository.findAll(); }

    // Get a grade by id
    public Grade getGradeBYId(String id){
        return gradeRepository.findById(id).get();
    }

    // Create a grade
    public Grade createGrade(GradeMapper gradeMapper){
        Grade newGrade = new Grade();
        try {
            if(gradeMapper.getId_evaluation()!=null){
                newGrade.setEvaluation(evalutionRepository.findById(gradeMapper.getId_evaluation()).get());
            }
            if(gradeMapper.getMark()!=null){
                newGrade.setMark(gradeMapper.getMark());
            }
            if(gradeMapper.getUser_id()!=null){
                newGrade.setUser(usersRepository.findById(gradeMapper.getUser_id()).get());
            }
           gradeRepository.save(newGrade);
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to create grade");
        }
        return gradeRepository.findById(newGrade.getId()).get();
    }

    // Update a grade
    @Transactional
    public Grade updateGrade(String id, GradeMapper gradeMapper){
        boolean gradeExists = gradeRepository.existsById(id);
        if (!gradeExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: Grade with id %s not found in database ", id));
        }

        Grade newGrade = gradeRepository.findById(id).get();
        try {

            if(gradeMapper.getId_evaluation()!=null
                    && !evalutionRepository.findById(gradeMapper.getId_evaluation()).get().equals(newGrade.getEvaluation())){
                newGrade.setEvaluation(evalutionRepository.findById(gradeMapper.getId_evaluation()).get());
            }
            if(gradeMapper.getMark()!=null && !gradeMapper.getMark().equals(newGrade.getMark())){
                newGrade.setMark(gradeMapper.getMark());
            }
            if(gradeMapper.getUser_id()!=null
                    && !evalutionRepository.findById(gradeMapper.getUser_id()).get().equals(newGrade.getUser())){
                newGrade.setUser(usersRepository.findById(gradeMapper.getUser_id()).get());
            }
            gradeRepository.save(newGrade);
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to update grade");
        }

        return gradeRepository.findById(id).get();
    }

    // Delete a grade by id
    public String deleteGradeById(String grade_id){
        boolean gradeExists = gradeRepository.existsById(grade_id);
        if (!gradeExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: Grade with id %s not found in database ", grade_id));
        }
        gradeRepository.deleteById(grade_id);
        return "Grade deleted With Success";
    }
}
