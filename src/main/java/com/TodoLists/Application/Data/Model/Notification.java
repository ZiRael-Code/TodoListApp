package com.TodoLists.Application.Data.Model;

import com.TodoLists.Application.Services.UserServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class Notification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private LocalDateTime timeSent;
    private String notificationTitle;
    private String notificationBody;
    private String notificationSender;

    public Notification(){
        timeSent = LocalDateTime.now();
    }

}
