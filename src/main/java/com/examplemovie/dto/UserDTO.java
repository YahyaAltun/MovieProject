package com.examplemovie.dto;

import com.examplemovie.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message="First Name can not be null")
    @NotBlank(message="First Name can not be white space")
    @Size(max=50, message="First Name '${validatedValue}' must be {max} chars long")
    private String firstName;

    @NotNull(message="Last Name can not be null")
    @NotBlank(message="Last Name can not be white space")
    @Size(max=50, message="Last Name '${validatedValue}' must be {max} chars long")
    private String lastName;

    @Email(message="Provide valid email")
    private String email;

    public UserDTO(User user){
        this.id= user.getId();
        this.firstName= user.getFirstName();
        this.lastName=user.getLastName();
        this.email= user.getEmail();
    }
}
