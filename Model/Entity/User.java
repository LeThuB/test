package TechMini.Model.Entity;

import TechMini.Constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "miniUser")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private String fullName;
    @Enumerated(EnumType.STRING ) //chi anh xa 1 gia tri trong database
    @Column(nullable = false)
    private Role role; //Set<ROLE> role
    private LocalDateTime createdAt;
}
