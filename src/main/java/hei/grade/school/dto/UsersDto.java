package hei.grade.school.mapper;

import hei.grade.school.model.Groupe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UsersMapper {
    private String firstName;
    private String lastName;
    private Character sex;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String email;
    private LocalDate entranceDatetime;
    private String status;
    private String id_groupe;
}
