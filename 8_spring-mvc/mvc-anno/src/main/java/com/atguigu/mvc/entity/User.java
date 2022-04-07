package com.atguigu.mvc.entity;

import java.util.Arrays;

public class User {

    private Integer id;

    private String username;

    private String password;

    private String[] hobby;

    private Pet pet;

    public User() {
    }

    public User(Integer id, String username, String password, String[] hobby) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hobby = hobby;
    }

    public User(Integer id, String username, String password, String[] hobby, Pet pet) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hobby = hobby;
        this.pet = pet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hobby=" + Arrays.toString(hobby) +
                ", pet=" + pet +
                '}';
    }
}
