package TechMini.Model.Dto.Response;

import TechMini.Constant.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegisterResponse {
    private Long id;
    private String userName;
    private String email;
    private String fullName;
    private Role role;
}
