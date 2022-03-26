package com.atguitu.boot.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    private String username;

    private String password;

}
