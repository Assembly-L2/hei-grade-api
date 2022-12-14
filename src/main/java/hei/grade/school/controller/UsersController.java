package hei.grade.school.controller;

import hei.grade.school.dto.UsersDto;
import hei.grade.school.model.Users;
import hei.grade.school.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    @GetMapping()
    public List<Users> getAllUsers(){ return usersService.getAllUsers(); }

    @GetMapping("/{user_id}")
    public Users getUserById(@PathVariable String user_id){ return usersService.getById(user_id); }

    @PostMapping()
    public Users createUser(@RequestBody UsersDto usersDto){ return usersService.createUser(usersDto); }

    @PutMapping("/{user_id}")
    public Users updateUser(
            @PathVariable String user_id,
            @RequestBody UsersDto usersDto
    ){
        return usersService.updateUser(user_id, usersDto);
    }

    @DeleteMapping("/{user_id}")
    public String deleteUser(@PathVariable String user_id){ return usersService.deleteUser(user_id); }

}
