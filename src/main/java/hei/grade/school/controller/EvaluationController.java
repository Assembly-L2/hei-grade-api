package hei.grade.school.controller;

import hei.grade.school.mapper.EvaluationMapper;
import hei.grade.school.model.Evaluation;
import hei.grade.school.service.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/evaluations")
public class EvaluationController {

    private EvaluationService evaluationService;

    @GetMapping()
    public List<Evaluation> getAllEvaluations(){ return evaluationService.getAllEvaluation(); }

    @GetMapping("/{id}")
    public Evaluation getEvaluationById(@PathVariable String id){
        return evaluationService.getEvaluationById(id);
    }

    @PostMapping()
    public Evaluation createEvaluation(@RequestBody EvaluationMapper evaluationMapper){
        return evaluationService.createEvaluation(evaluationMapper);
    }

    @PutMapping("/{evaluation_id}")
    public Evaluation updateEvaluation(
            @PathVariable String evaluation_id,
            @RequestBody EvaluationMapper evaluationMapper
                                       ){
        return evaluationService.updateEvaluation(evaluation_id, evaluationMapper);
    }

    @DeleteMapping("/{evalutation_id}")
    public String deleteEvaluation(@PathVariable String evalutation_id){
        return evaluationService.deleteEvaluationById(evalutation_id);
    }
}
