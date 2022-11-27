package hei.grade.school.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Mention {
    @Id
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();
    private String name;
    private Float startNote;
    private Float endNote;

    public Mention(String name, Float startNote, Float endNote){
        this.name=name;
        this.startNote=startNote;
        this.endNote=endNote;
    }
}
