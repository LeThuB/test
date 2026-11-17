package TechMini.Model.Dto.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
   @NotBlank(message = "Username khong duoc de trong")
   @Size(min = 4,max = 50,message = "Username phai co do dai tu 4 - 50 ky tu ")
    private String username;
 @NotBlank(message = "Email khong duoc de trong")
 @Email(message = "Email khong dung dinh dang")
private String email;
 @NotBlank(message = "Mat khau khong duoc de trong")
 @Size(min = 6,message = "Mat khau toi thieu 6 ky tu ")
    private String password;
    private String fullName;
}
