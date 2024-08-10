package com.TodoLists.Data.Model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToDoItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int todoItemId;
    private String title;
    private String description;

//    @JsonIgnore

//    @JsonProperty("dueDate")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", shape = JsonFormat.Shape.OBJECT)
    private LocalDateTime dueDate;
    //    @JsonIgnore
    //    @JsonProperty("startDate")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", shape = JsonFormat.Shape.OBJECT)
    private LocalDateTime startDate;

//    @JsonIgnore
//    @JsonProperty("createdDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", shape = JsonFormat.Shape.OBJECT)
    private LocalDateTime createdDate;


    private TaskStatus taskStatus;
    private Priority priority;
    private boolean completed = false;
    private String taskName;
    private String taskType;


    private int userId;


    public ToDoItem(){
        this.createdDate = LocalDateTime.now();
    }

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse("2024-08-09T14:30", formatter);
        System.out.println("Parsed LocalDateTime: " + localDateTime);
    }

}
