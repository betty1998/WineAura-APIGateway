package com.mercury.apigateway.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
   private int id;
    private String username;
    private String password;

    private Role role;
    private String status;


}
