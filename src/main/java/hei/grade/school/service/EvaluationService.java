package hei.grade.school.service;

import hei.grade.school.dto.EvaluationDto;
import hei.grade.school.model.Evaluation;
import hei.grade.school.repository.CourseRepository;
import hei.grade.school.repository.EvalutionRepository;
import hei.grade.school.repository.SemesterRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EvaluationService {

    private EvalutionRepository evalutionRepository;
    private SemesterRepository semesterRepository;
    private CourseRepository courseRepository;

    // Get all evaluations
    public List<Evaluation> getAllEvaluation(){ return evalutionRepository.findAll(); }

    // Get an evaluation by id
    public Evaluation getEvaluationById(String evaluation_id){
        return evalutionRepository.findById(evaluation_id).get();
    }

    // Create evaluation
    public Evaluation createEvaluation(EvaluationDto evaluationDto){
        Evaluation newEvaluation = new Evaluation();

        try {
            if(evaluationDto.getDateExam()!=null){
                String date = evaluationDto.getDateExam().toString();
                newEvaluation.setDateExam(LocalDate.parse(date));
            }
            if(evaluationDto.getStatus()!=null){

                newEvaluation.setStatus(evaluationDto.getStatus());
            }
            if(evaluationDto.getSemester_id()!=null){

                newEvaluation.setSemester(semesterRepository.findById(evaluationDto.getSemester_id()).get());
            }
            if (evaluationDto.getCourse_id()!=null){
                newEvaluation.setCourse(courseRepository.findById(evaluationDto.getCourse_id()).get());
            }

            evalutionRepository.save(newEvaluation);
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to create evaluation");
        }
        return evalutionRepository.findById(newEvaluation.getId()).get();
    }


    @Transactional // Update evaluation by id
    public Evaluation updateEvaluation(String evaluation_id, EvaluationDto evaluationDto){

        boolean evaluationExists = evalutionRepository.existsById(evaluation_id);
        if (!evaluationExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: Evaluation with id %s not found in database ", evaluation_id));
        }

        try {
            Evaluation evaluation = evalutionRepository.findById(evaluation_id).get();

            if(evaluationDto.getDateExam()!=null
                    && !evaluationDto.getDateExam().equals(evaluation.getDateExam())){
                String date = String.valueOf(evaluationDto.getDateExam());
                evaluation.setDateExam(LocalDate.parse(date));
            }
            if(evaluationDto.getStatus()!=null
                    && !evaluationDto.getStatus().equals(evaluation.getStatus())){

                evaluation.setStatus(evaluationDto.getStatus());
            }
            if(evaluationDto.getSemester_id()!=null
                    && !semesterRepository.findById(evaluationDto.getSemester_id()).get().equals(evaluation.getSemester())){

                evaluation.setSemester(semesterRepository.findById(evaluationDto.getSemester_id()).get());
            }
            if (evaluationDto.getCourse_id()!=null
                    && !courseRepository.findById(evaluationDto.getCourse_id()).get().equals(evaluation.getCourse())){
                evaluation.setCourse(courseRepository.findById(evaluationDto.getCourse_id()).get());
            }

            evalutionRepository.save(evaluation);
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to update evaluation");
        }


        return evalutionRepository.findById(evaluation_id).get();
    }

    // Delete an evaluation by id
    public String deleteEvaluationById(String evaluation_id){
        boolean evaluationExists = evalutionRepository.existsById(evaluation_id);
        if (!evaluationExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: Evaluation with id %s not found in database ", evaluation_id));
        }
        evalutionRepository.deleteById(evaluation_id);
        return "Evaluation deleted With Success";
    }
}
