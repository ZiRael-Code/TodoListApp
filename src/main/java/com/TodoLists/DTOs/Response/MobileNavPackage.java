package com.TodoLists.DTOs.Response;

import com.TodoLists.DTOs.Request.Dashboard;
import com.TodoLists.DTOs.Request.TodayTask;
import com.TodoLists.Data.Model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class MobileNavPackage {
    private Dashboard dashboard;
    private TodayTask todayTask;
    private User user;

}
