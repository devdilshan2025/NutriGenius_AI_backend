package ecom.icet.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private Double weight;
    private Double height;
    private Integer age;
}