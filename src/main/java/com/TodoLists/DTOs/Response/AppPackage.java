package com.TodoLists.DTOs.Response;

import com.TodoLists.DTOs.Request.Dashboard;
import com.TodoLists.DTOs.Request.FindTaskPackage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppPackage {
    private Dashboard dashboard;
    private FindTaskPackage findTaskPackage;

}
