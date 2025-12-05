package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private String id;

    private String name;

    private String role;

    private String phone;

    private boolean active;

}
