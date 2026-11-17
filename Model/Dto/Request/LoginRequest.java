package TechMini.Model.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "Email khong duoc de trong")
    @Email(message = "Email phai dung dinh dang")
    private String email;
    @NotBlank(message = "Mat khau khong duoc de trong")
    @Size(min = 6,message = "Mat khau toi thieu 6 ky tu ")
    private String password;
}
