package com.TodoLists.DTOs.Request;

import lombok.Data;

@Data
public class ForgetPasswordRequest {
 private String username;
 private String oldPassword;
 private String newPassword;
}
