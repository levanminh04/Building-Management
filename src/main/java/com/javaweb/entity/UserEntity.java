package com.javaweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = -4988455421375043688L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();


//    @OneToMany(mappedBy="userEntity", fetch = FetchType.LAZY)
//    private List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();

//    @OneToMany(mappedBy="users", fetch = FetchType.LAZY)
//    private List<UserRoleEntity> userRoleEntities = new ArrayList<>();


    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<BuildingEntity> buildings = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<CustomerEntity> customers = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Tài khoản không hết hạn
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Mật khẩu không hết hạn
    }



    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1; // status = 1 => trả về True => nghĩa là tài khoản không bị khóa ngược lại trả về false
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1; // status = 1 nghĩa là tài khoản hoạt động
    }

}
