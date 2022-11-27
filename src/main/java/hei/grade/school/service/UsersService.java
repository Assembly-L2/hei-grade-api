package hei.grade.school.service;

import hei.grade.school.dto.UsersDto;
import hei.grade.school.model.Users;
import hei.grade.school.repository.GroupeRepository;
import hei.grade.school.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {
    private UsersRepository usersRepository;
    private GroupeRepository groupeRepository;

    // Get all users
    public List<Users> getAllUsers(){ return usersRepository.findAll(); }

    // Get user by id
    public Users getById(String id){ return usersRepository.findById(id).get(); }

    // Create user
    public Users createUser(UsersDto usersDto){
        Users user = new Users();
        try {
            if(usersDto.getSex()!=null){
                user.setSex(usersDto.getSex());
            }
            if(usersDto.getId_groupe()!=null){
                user.setGroupe(groupeRepository.findById(usersDto.getId_groupe()).get());
            }
            if(usersDto.getPhone()!=null){
                user.setPhone(usersDto.getPhone());
            }
            if(usersDto.getEntranceDatetime()!=null){
                String date = String.valueOf(usersDto.getEntranceDatetime());
                user.setEntranceDatetime(LocalDate.parse(date));
            }
            if(usersDto.getBirthDate()!=null){
                String date = String.valueOf(usersDto.getBirthDate());
                user.setBirthDate(LocalDate.parse(date));
            }
            if(usersDto.getStatus()!=null){
                user.setStatus(usersDto.getStatus());
            }
            if(usersDto.getEmail()!=null){
                user.setEmail(usersDto.getEmail());
            }
            if(usersDto.getAddress()!=null){
                user.setAddress(usersDto.getAddress());
            }
            if(usersDto.getLastName()!=null){
                user.setLastName(usersDto.getLastName());
            }
            if(usersDto.getFirstName()!=null){
                user.setFirstName(usersDto.getFirstName());
            }

          usersRepository.save(user);
       } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to create user");
        }
        return usersRepository.findById(user.getId()).get();
    }

    // Update user
    public Users updateUser(String id, UsersDto usersDto){
        Boolean userExists = usersRepository.existsById(id);
        if(!userExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: user with id %s not found in database ", id));
        }
        Users user = usersRepository.findById(id).get();
        try {
            if(usersDto.getSex()!=null
                    && !usersDto.getSex().equals(user.getSex())){
                user.setSex(usersDto.getSex());
            }
            if(usersDto.getId_groupe()!=null
                    && !groupeRepository.findById(usersDto.getId_groupe()).get().equals(user.getGroupe())){
                user.setGroupe(groupeRepository.findById(usersDto.getId_groupe()).get());

            }
            if(usersDto.getPhone()!=null
                    && !usersDto.getPhone().equals(user.getPhone())){
                user.setPhone(usersDto.getPhone());
            }
            if(usersDto.getEntranceDatetime()!=null
                    && !usersDto.getEntranceDatetime().equals(user.getEntranceDatetime())){
                String date = String.valueOf(usersDto.getEntranceDatetime());
                user.setEntranceDatetime(LocalDate.parse(date));
            }
            if(usersDto.getBirthDate()!=null
                    && !usersDto.getBirthDate().equals(user.getBirthDate())){
                String date = String.valueOf(usersDto.getBirthDate());
                user.setBirthDate(LocalDate.parse(date));
            }
            if(usersDto.getStatus()!=null
                    && !usersDto.getStatus().equals(user.getStatus())){
                user.setStatus(usersDto.getStatus());
            }
            if(usersDto.getEmail()!=null
                    && !usersDto.getEmail().equals(user.getEmail())){
                user.setEmail(usersDto.getEmail());
            }
            if(usersDto.getAddress()!=null
                    && !usersDto.getAddress().equals(user.getAddress())){
                user.setAddress(usersDto.getAddress());
            }
            if(usersDto.getLastName()!=null
                    && !usersDto.getLastName().equals(user.getLastName())){
                user.setLastName(usersDto.getLastName());
            }
            if(usersDto.getFirstName()!=null
                    && !usersDto.getFirstName().equals(user.getFirstName())){
                user.setFirstName(usersDto.getFirstName());
            }

            usersRepository.save(user);
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to update user");
        }

        return usersRepository.findById(id).get();
    }

    // Delete user
    public String deleteUser(String id){
        Boolean userExists = usersRepository.existsById(id);
        if(!userExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: user with id %s not found in database ", id));
        }
        usersRepository.deleteById(id);
        return "User deleted with success";
    }

}
