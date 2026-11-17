package TechMini.Security;
import SS12.Security.JwtAuthenticationEntryPoint;
import TechMini.Model.Dto.Response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtEntryPoint implements AuthenticationEntryPoint,
        AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String error = (String) request.getAttribute("errors");
        if (error == null){error = "Token khong hop le hoac thieu token";}
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .status(401)
                .message(error)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
        objectMapper.writeValue(response.getOutputStream(),apiResponse);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .status(403)
                .message("Ban khong co quyen truy cap")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
        objectMapper.writeValue(response.getOutputStream(),apiResponse);
    }
}
