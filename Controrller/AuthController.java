package TechMini.Controrller;

import TechMini.Model.Dto.Request.LoginRequest;
import TechMini.Model.Dto.Request.RegisterRequest;
import TechMini.Model.Dto.Response.ApiResponse;
import TechMini.Model.Dto.Response.LoginResponse;
import TechMini.Model.Dto.Response.RegisterResponse;
import TechMini.Model.Entity.User;
import TechMini.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest registerRequest){
  User user = userService.registerUser(registerRequest);
  RegisterResponse registerResponse = RegisterResponse.builder()
          .id(user.getId())
          .userName(user.getUsername())
          .email(user.getEmail())
          .fullName(user.getFullName())
          .role(user.getRole())
          .build();
  ApiResponse<RegisterResponse> response = ApiResponse.<RegisterResponse>builder()
          .status(201)
          .message("Dang ky thanh cong")
          .data(registerResponse)
          .timestamp(LocalDateTime.now())
          .build();
  return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    //login
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest){
       LoginResponse loginResponse = userService.login(loginRequest);
       ApiResponse<LoginResponse>  response = ApiResponse.<LoginResponse>builder()
               .status(200)
               .message("Dang nhap thanh cong")
               .data(loginResponse)
               .timestamp(LocalDateTime.now())
               .build();

       return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
