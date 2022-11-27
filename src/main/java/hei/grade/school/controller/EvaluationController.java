package hei.grade.school.controller;

import hei.grade.school.dto.EvaluationDto;
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

    @GetMapping("/{evaluation_id}")
    public Evaluation getEvaluationById(@PathVariable String evaluation_id){
        return evaluationService.getEvaluationById(evaluation_id);
    }

    @PostMapping()
    public Evaluation createEvaluation(@RequestBody EvaluationDto evaluationDto){
        return evaluationService.createEvaluation(evaluationDto);
    }

    @PutMapping("/{evaluation_id}")
    public Evaluation updateEvaluation(
            @PathVariable String evaluation_id,
            @RequestBody EvaluationDto evaluationDto
                                       ){
        return evaluationService.updateEvaluation(evaluation_id, evaluationDto);
    }

    @DeleteMapping("/{evalutation_id}")
    public String deleteEvaluation(@PathVariable String evalutation_id){
        return evaluationService.deleteEvaluationById(evalutation_id);
    }
}
