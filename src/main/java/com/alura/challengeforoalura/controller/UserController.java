package com.alura.challengeforoalura.controller;

import com.alura.challengeforoalura.domain.course.CourseData;
import com.alura.challengeforoalura.domain.topic.*;
import com.alura.challengeforoalura.domain.users.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserResponseData> registerUsers(
            @RequestBody @Valid UserRegistrationData userRegistrationData,
            UriComponentsBuilder uriComponentsBuilder) {
        User user = userRepository.save(new User(userRegistrationData));
        UserResponseData userResponseData = new UserResponseData(user.getId(), user.getName(),
                user.getPassword(), user.getEmail());
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(userResponseData);
    }

    @GetMapping
    public Page<DataUserList> ListOfUser(@PageableDefault(size = 3) Pageable pageable) {
        return userRepository.findByActiveTrue(pageable).map(DataUserList::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody @Valid UserUpdateData userUpdateData) {
        User user = userRepository.getReferenceById(userUpdateData.id());
        user.updateData(userUpdateData);
        return ResponseEntity.ok(new UserResponseData(
                user.getId(), user.getName(), user.getPassword(), user.getEmail()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        user.disabledUser();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseData> returnUserDate(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        var userData = new UserResponseData(
                user.getId(), user.getName(), user.getPassword(), user.getEmail());
        return ResponseEntity.ok(userData);
    }


}
