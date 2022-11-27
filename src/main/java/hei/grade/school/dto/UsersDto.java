package hei.grade.school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UsersDto {
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
