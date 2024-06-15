package com.TodoLists.Data.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
public class ToDoItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int todoItemId;
    private String title;
    private String description;

    @JsonIgnore
    @JsonProperty("dueDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate dueDate;

    @JsonIgnore
    @JsonProperty("dueDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate startDate;

    @JsonIgnore
    @JsonProperty("createdDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;



    private Priority priority;
    private boolean completed = false;
    private String taskName;
    private String taskType;


    private int userId;
    private String startTimer;
    private String endTimer;


    public ToDoItem(){
        this.createdDate = LocalDateTime.now();
    }

}
