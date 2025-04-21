package com.TodoLists.Data.Model;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Notification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
//    private LocalDateTime timeSent;
    private String from;
    private String notificationTitle;
    private String notificationBody;
    private int taskId;

    private LocalDateTime timeSent;
//    private Map<>

    public Notification(){
        timeSent = LocalDateTime.now();
    }
}
