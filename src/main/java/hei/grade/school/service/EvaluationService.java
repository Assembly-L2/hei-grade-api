package hei.grade.school.service;

import hei.grade.school.mapper.EvaluationMapper;
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
    public Evaluation createEvaluation(EvaluationMapper evaluationMapper){
        Evaluation newEvaluation = new Evaluation();

        try {
            if(evaluationMapper.getDateExam()!=null){
                String date = evaluationMapper.getDateExam().toString();
                newEvaluation.setDateExam(LocalDate.parse(date));
            }
            if(evaluationMapper.getStatus()!=null){

                newEvaluation.setStatus(evaluationMapper.getStatus());
            }
            if(evaluationMapper.getSemester_id()!=null){

                newEvaluation.setSemester(semesterRepository.findById(evaluationMapper.getSemester_id()).get());
            }
            if (evaluationMapper.getCourse_id()!=null){
                newEvaluation.setCourse(courseRepository.findById(evaluationMapper.getCourse_id()).get());
            }

            evalutionRepository.save(newEvaluation);
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to create evaluation");
        }
        return evalutionRepository.findById(newEvaluation.getId()).get();
    }


    @Transactional // Update evaluation by id
    public Evaluation updateEvaluation(String evaluation_id, EvaluationMapper evaluationMapper){

        boolean evaluationExists = evalutionRepository.existsById(evaluation_id);
        if (!evaluationExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: Evaluation with id %s not found in database ", evaluation_id));
        }

        try {
            Evaluation evaluation = evalutionRepository.findById(evaluation_id).get();

            if(evaluationMapper.getDateExam()!=null
                    && !evaluationMapper.getDateExam().equals(evaluation.getDateExam())){
                String date = String.valueOf(evaluationMapper.getDateExam());
                evaluation.setDateExam(LocalDate.parse(date));
            }
            if(evaluationMapper.getStatus()!=null
                    && !evaluationMapper.getStatus().equals(evaluation.getStatus())){

                evaluation.setStatus(evaluationMapper.getStatus());
            }
            if(evaluationMapper.getSemester_id()!=null
                    && !semesterRepository.findById(evaluationMapper.getSemester_id()).get().equals(evaluation.getSemester())){

                evaluation.setSemester(semesterRepository.findById(evaluationMapper.getSemester_id()).get());
            }
            if (evaluationMapper.getCourse_id()!=null
                    && !courseRepository.findById(evaluationMapper.getCourse_id()).get().equals(evaluation.getCourse())){
                evaluation.setCourse(courseRepository.findById(evaluationMapper.getCourse_id()).get());
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
