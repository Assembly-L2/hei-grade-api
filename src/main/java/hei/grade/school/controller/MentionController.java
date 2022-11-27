package hei.grade.school.controller;

import hei.grade.school.model.Mention;
import hei.grade.school.service.MentionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/mentions")
public class MentionController {
    private MentionService mentionService;

    @GetMapping()
    public List<Mention> getAllMentions(){ return mentionService.getAllMention(); }

    @GetMapping("/{mention_id}")
    public Mention getMentionById(@PathVariable String mention_id){ return mentionService.getMentionById(mention_id); }

    @PostMapping()
    public Mention createMention(@RequestBody Mention mention){ return mentionService.createMention(mention); }

    @PutMapping("/{mention_id}")
    public Mention updateMention(
            @PathVariable String mention_id,
            @RequestBody Mention mention
    ){
        return mentionService.updateMention(mention_id, mention);
    }

    @DeleteMapping("/{mention_id}")
    public String deleteMention(@PathVariable String mention_id){ return mentionService.deleteMention(mention_id); }
}
