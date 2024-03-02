package org.thatmovie.model;

import io.swagger.v3.core.converter.AnnotatedType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "member")
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SERIAL")
    private Integer id;

    @Column(name = "name", length = 256, nullable = false)
    private String name;
    @Column(name = "surname", length = 256)
    private String surname;
    @Column(name = "username", length = 256, nullable = false)
    private String username;
    @Column(name = "email", length = 256)
    private String email;
    @Column(name = "password", length = 256, nullable = false)
    private String password;
    @Column(name = "avatar", length = 256)
    private String avatar;
    @Column(name = "bio", length = 256)
    private String bio;


    @OneToMany(mappedBy = "member")
    private List<Review> reviews;


    @OneToMany(mappedBy = "member")
    private List<PlayList> playlists;

    public Member( String name, String surname, String username, String email, String password) {

        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Member() {
        this(-1, "name", "surname", "username","email", "password", "avatar", "bio");
    }

    public Member(Integer id, String name, String surname, String username, String email, String password, String avatar, String bio) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.bio = bio;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
