package hei.grade.school.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import hei.grade.school.model.Semester;
import hei.grade.school.repository.SemesterRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class SemesterService {

    private SemesterRepository semesterRepository;

    public List<Semester> findAllSemester() {
        return semesterRepository.findAll();
    }

    public Semester addSemester(Semester semester) {
        Semester newSemester = new Semester();
       try {
           if(semester.getStatus()!=null){
               newSemester.setStatus(semester.getStatus());
           }
           if(semester.getName()!=null){
               newSemester.setName(semester.getName());
           }

           if(semester.getStartDate()!=null){
               String date = String.valueOf(semester.getStartDate());
               newSemester.setStartDate(LocalDate.parse(date));
           }
           if(semester.getEndDate()!=null){
               String date2 = String.valueOf(semester.getEndDate());
               newSemester.setEndDate(LocalDate.parse(date2));
           }

        semesterRepository.save(newSemester);
       } catch ( ResponseStatusException e){
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Server Error: Unable to update semester");
        }
        return semesterRepository.findById(newSemester.getId()).get();
    }

    public Semester findSemesterById(String id) {
        return semesterRepository.findById(id).get();
    }

    @Transactional
    public Semester updateSemesterById(String id, Semester semester) {

        Boolean semesterExists =  semesterRepository.existsById(id);
        if(!semesterExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: semester with id %s not found in database ", id));
        }

        try {
            Semester newSemester = semesterRepository.findById(id).get();
            if(semester.getStatus()!=null
                    && !semester.getStatus().equals(newSemester.getStatus())){
                newSemester.setStatus(semester.getStatus());
            }
            if(semester.getName()!=null
                    && !semester.getName().equals(newSemester.getName())){
                newSemester.setName(semester.getName());
            }

            if(semester.getStartDate()!=null
                    && !semester.getStartDate().equals(newSemester.getStartDate())){
                String date = String.valueOf(semester.getStartDate());
                newSemester.setStartDate(LocalDate.parse(date));
            }
            if(semester.getEndDate()!=null
                    && !semester.getEndDate().equals(newSemester.getEndDate())){
                String date2 = String.valueOf(semester.getEndDate());
                newSemester.setEndDate(LocalDate.parse(date2));
            }

            semesterRepository.save(newSemester);
        } catch (ResponseStatusException e){
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Server Error: Unable to update semester");
        }

        return semesterRepository.findById(id).get();
    }

    public String deleteSemesterById(String id) {
        Boolean semesterExists =  semesterRepository.existsById(id);
        if(!semesterExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: semester with id %s not found in database ", id));
        }

        semesterRepository.deleteById(id);
        return "Evaluation deleted With Success";
    }

}
