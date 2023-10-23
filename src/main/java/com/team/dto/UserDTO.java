package com.team.dto;

import com.team.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
    private Integer id;
    @NotNull(message = "{user.name.notpresent}")
    @Pattern(regexp = "^[A-Za-z\\s-]*$", message = "Name must contain only letters and spaces or hyphens")
    private String name;
    @NotNull(message = "{user.name.notpresent}")
    @Pattern(regexp = "^[A-Za-z\\s-]*$", message = "Surname must contain only letters, spaces, or hyphens")
    private String surname;
    @NotNull(message = "{user.name.notpresent}")
    @Email(message = "{email.not.valid}")
    private String email;

    public UserDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserDTO createDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
    public static User createEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
