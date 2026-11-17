package TechMini.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor

public class JwtFilterChain extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserDetaiServiceCustom userDetaiServiceCustom;
    //tao phuong thuc lay token
public String getJwtFromRequest(HttpServletRequest request){
   //lay gia thi cua authorization trong http header
    String bearerToken = request.getHeader("Authorization");
if (bearerToken !=null && bearerToken.startsWith("Bearer ")){
    return bearerToken.substring(7);

}
return null;
}
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
String token = getJwtFromRequest(request);
if (token !=null && jwtProvider.validToken(token)){
    String username = jwtProvider.getUsernameForomToken(token);

    //goi userdetai lay thong tin quyen
    UserDetails userDetails = userDetaiServiceCustom.loadUserByUsername(username);
    //tao doi tuong xac thuc
    UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
}
filterChain.doFilter(request,response);
    }
}
