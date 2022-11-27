package hei.grade.school.service;

import hei.grade.school.model.Mention;
import hei.grade.school.repository.MentionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class MentionService {

    private MentionRepository mentionRepository;

    // Get All mentions
    public List<Mention> getAllMention(){ return mentionRepository.findAll(); }

    // Get mention by id
    public Mention getMentionById(String id_mention){ return mentionRepository.findById(id_mention).get(); }

    // Create mention
    public Mention createMention(Mention mention){
        Mention newMention = new Mention();
        try {
            if(mention.getEndNote()!=null){
                newMention.setEndNote(mention.getEndNote());
            }
            if(mention.getStartNote()!=null){
                newMention.setStartNote(mention.getStartNote());
            }
            if(mention.getName()!=null){
                newMention.setName(mention.getName());
            }

            mentionRepository.save(newMention);
        }catch (ResponseStatusException e){
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to create mention");
        }

        return mentionRepository.findById(newMention.getId()).get();
    }

    // Update mention
    public Mention updateMention(String id_mention, Mention mention){
        Boolean mentionExists = mentionRepository.existsById(id_mention);
        if(!mentionExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: mention with id %s not found in database ", id_mention));
        }

        Mention newMention = mentionRepository.findById(id_mention).get();
        try {
            if(mention.getEndNote()!=null
                    && !mention.getEndNote().equals(newMention.getEndNote())){
                newMention.setEndNote(mention.getEndNote());
            }
            if(mention.getStartNote()!=null
                    && ! mention.getStartNote().equals(newMention.getStartNote())){
                newMention.setStartNote(mention.getStartNote());
            }
            if(mention.getName()!=null
                    && !mention.getName().equals(newMention.getName())){
                newMention.setName(mention.getName());
            }

            mentionRepository.save(newMention);
        }catch (ResponseStatusException e){
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to update mention");
        }

        return mentionRepository.findById(id_mention).get();
    }

    // Delete Mention
    public String deleteMention(String id_mention){
        Boolean mentionExists = mentionRepository.existsById(id_mention);
        if(!mentionExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: mention with id %s not found in database ", id_mention));
        }
        mentionRepository.deleteById(id_mention);
        return "Mention deleted successfully";
    }
}
