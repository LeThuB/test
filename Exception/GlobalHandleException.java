package TechMini.Exception;

import TechMini.Exception.CustomException.DulicateResourceException;
import TechMini.Exception.CustomException.ResourceNotFoundException;
import TechMini.Model.Dto.Response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleException {
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponse<Map<String,String>>> validexception(MethodArgumentNotValidException ex){
  Map<String,String> errors = new HashMap<>();
  ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
  });
    ApiResponse<Map<String,String>> response = ApiResponse.<Map<String,String>>builder()
            .status(400)
            .message("Loi Validate ")
            .data(errors)
            .timestamp(LocalDateTime.now())
            .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
}

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse<String>> resourceNOtFound(ResourceNotFoundException ex){
    ApiResponse<String> response = ApiResponse.<String>builder()
            .status(404)
            .message(ex.getMessage())
            .data(null)
            .timestamp(LocalDateTime.now())
            .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
}

    @ExceptionHandler(DulicateResourceException.class)
    public ResponseEntity<ApiResponse<String>> dulicateResource(DulicateResourceException ex){
        ApiResponse<String> response = ApiResponse.<String>builder()
                    .status(409)
                    .message(ex.getMessage())
                    .data(null)
                    .timestamp(LocalDateTime.now())
                    .build();

            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<String>> badCredentials(BadCredentialsException ex ){
    ApiResponse<String> response = ApiResponse.<String>builder()
            .status(401)
            .message("Mat khau hoac email khong chinh xac")
            .data(null)
            .timestamp(LocalDateTime.now())
            .build();
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> accessDenied(AccessDeniedException ex){
    ApiResponse<String> response = ApiResponse.<String>builder()
            .status(403)
            .message("ban khong co quyen truy cap")
            .data(null)
            .timestamp(LocalDateTime.now())
            .build() ;
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> exceptionError(Exception ex){
    ApiResponse<String> response = ApiResponse.<String>builder()
            .status(500)
            .message("Loi he thong")
            .data(null)
            .timestamp(LocalDateTime.now())
            .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
