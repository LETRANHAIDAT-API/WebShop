package com.example.cv.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users" ,uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(name = "users.tk",query = "select u from users u")
public class users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "passwordd")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "User_Id",referencedColumnName = "Id"),inverseJoinColumns =
    @JoinColumn(name = "Role_Id",referencedColumnName = "Id"))
    private Set<Role> roles = new HashSet<>();
    public users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    // tra ve danh sach quyen cua nguoi dung
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.stream().forEach(i ->authorities.add(new SimpleGrantedAuthority(i.getName())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }

    // tra ve nam
    @Override
    public String getUsername() {
        return "";
    }

    // kt xem accout cp het hn ko
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }


    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
