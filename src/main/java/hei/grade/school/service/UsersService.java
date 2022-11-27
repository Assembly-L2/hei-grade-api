package hei.grade.school.service;

import hei.grade.school.mapper.UsersMapper;
import hei.grade.school.model.Users;
import hei.grade.school.repository.GroupeRepository;
import hei.grade.school.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
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
    public Users createUser(UsersMapper usersMapper){
        Users user = new Users();
        try {
            if(usersMapper.getSex()!=null){
                user.setSex(usersMapper.getSex());
            }
            if(usersMapper.getId_groupe()!=null){
                user.setGroupe(groupeRepository.findById(usersMapper.getId_groupe()).get());
            }
            if(usersMapper.getPhone()!=null){
                user.setPhone(usersMapper.getPhone());
            }
            if(usersMapper.getEntranceDatetime()!=null){
                String date = String.valueOf(usersMapper.getEntranceDatetime());
                user.setEntranceDatetime(LocalDate.parse(date));
            }
            if(usersMapper.getBirthDate()!=null){
                String date = String.valueOf(usersMapper.getBirthDate());
                user.setBirthDate(LocalDate.parse(date));
            }
            if(usersMapper.getStatus()!=null){
                user.setStatus(usersMapper.getStatus());
            }
            if(usersMapper.getEmail()!=null){
                user.setEmail(usersMapper.getEmail());
            }
            if(usersMapper.getAddress()!=null){
                user.setAddress(usersMapper.getAddress());
            }
            if(usersMapper.getLastName()!=null){
                user.setLastName(usersMapper.getLastName());
            }
            if(usersMapper.getFirstName()!=null){
                user.setFirstName(usersMapper.getFirstName());
            }

          usersRepository.save(user);
       } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to create user");
        }
        return usersRepository.findById(user.getId()).get();
    }

    // Update user
    public Users updateUser(String id, UsersMapper usersMapper){
        Boolean userExists = usersRepository.existsById(id);
        if(!userExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: user with id %s not found in database ", id));
        }
        Users user = usersRepository.findById(id).get();
        try {
            if(usersMapper.getSex()!=null
                    && !usersMapper.getSex().equals(user.getSex())){
                user.setSex(usersMapper.getSex());
            }
            if(usersMapper.getId_groupe()!=null
                    && !groupeRepository.findById(usersMapper.getId_groupe()).get().equals(user.getGroupe())){
                user.setGroupe(groupeRepository.findById(usersMapper.getId_groupe()).get());

            }
            if(usersMapper.getPhone()!=null
                    && !usersMapper.getPhone().equals(user.getPhone())){
                user.setPhone(usersMapper.getPhone());
            }
            if(usersMapper.getEntranceDatetime()!=null
                    && !usersMapper.getEntranceDatetime().equals(user.getEntranceDatetime())){
                String date = String.valueOf(usersMapper.getEntranceDatetime());
                user.setEntranceDatetime(LocalDate.parse(date));
            }
            if(usersMapper.getBirthDate()!=null
                    && !usersMapper.getBirthDate().equals(user.getBirthDate())){
                String date = String.valueOf(usersMapper.getBirthDate());
                user.setBirthDate(LocalDate.parse(date));
            }
            if(usersMapper.getStatus()!=null
                    && !usersMapper.getStatus().equals(user.getStatus())){
                user.setStatus(usersMapper.getStatus());
            }
            if(usersMapper.getEmail()!=null
                    && !usersMapper.getEmail().equals(user.getEmail())){
                user.setEmail(usersMapper.getEmail());
            }
            if(usersMapper.getAddress()!=null
                    && !usersMapper.getAddress().equals(user.getAddress())){
                user.setAddress(usersMapper.getAddress());
            }
            if(usersMapper.getLastName()!=null
                    && !usersMapper.getLastName().equals(user.getLastName())){
                user.setLastName(usersMapper.getLastName());
            }
            if(usersMapper.getFirstName()!=null
                    && !usersMapper.getFirstName().equals(user.getFirstName())){
                user.setFirstName(usersMapper.getFirstName());
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
