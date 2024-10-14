package com.pillgood.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    
    private Long id;
    private String email;
    private String password;
    private String name;
    private Date birthday;
    private String gender;
    private String phone;
    private String provider;
    private String token;
    private String role;
    
    @Default
    private Boolean deleted=false;
    
    @Data
    public static class UpdateDto{
    	  private Long id;
    	  private String email;
    	  private String password;
    	  private String oldPassword;
    }
    @Data
    public static class JoinDto{
    	private String email;
    	private String password;
    	private String name;
    	private Date birthday;
        private String gender;
        private String phone;
    }
    @Data
    public static class LoginDto {
        private String email;
        private String password;
    }
    @Data
    public static class CodeCheckDto{
    	private String email;
    	private String code;
    }
}
