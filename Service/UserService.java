package TechMini.Service;

import TechMini.Constant.Role;
import TechMini.Exception.CustomException.DulicateResourceException;
import TechMini.Exception.CustomException.ResourceNotFoundException;
import TechMini.Model.Dto.Request.LoginRequest;
import TechMini.Model.Dto.Request.RegisterRequest;
import TechMini.Model.Dto.Response.ApiResponse;
import TechMini.Model.Dto.Response.LoginResponse;
import TechMini.Model.Entity.User;
import TechMini.Repository.UserRepository;
import TechMini.Security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final JwtProvider jwtProvider;
    //register
    public User registerUser(RegisterRequest registerRequest){
     if (userRepository.existsByEmail(registerRequest.getEmail()) ){
         throw new  DulicateResourceException("Email da ton tai");
     }
     if (userRepository.existsByUsername(registerRequest.getUsername())){
         throw new DulicateResourceException("Username da ton tai");
     }
     User user = User.builder()
             .username(registerRequest.getUsername())
             .email(registerRequest.getEmail())
             .fullName(registerRequest.getFullName())
             .password(passwordEncoder.encode(registerRequest.getPassword()))
             .role(Role.ROLE_USER) //role la set :Set.of(Role.ROLE_USER)
             .createdAt(LocalDateTime.now())
             .build();

     return userRepository.save(user);
    }

    //login
    public LoginResponse login(LoginRequest loginRequest){
      User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->
              new BadCredentialsException("Email hoac mat khau khong chinh xac"));
      if (!passwordEncoder.matches( loginRequest.getPassword(),user.getPassword())){
          throw new BadCredentialsException("Email hoac mat khau khong dung");
      }
      String accessToken = jwtProvider.generateAccessToken(user);
  return new LoginResponse(user.getUsername(),accessToken);


    }
}
