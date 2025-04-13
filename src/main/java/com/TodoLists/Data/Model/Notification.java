package com.TodoLists.Data.Model;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Notification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
//    private LocalDateTime timeSent;
    private String notificationTitle;
    private String notificationBody;
    private String from;
    private LocalDateTime timeSent;

    public Notification(){
        timeSent = LocalDateTime.now();
    }
}
