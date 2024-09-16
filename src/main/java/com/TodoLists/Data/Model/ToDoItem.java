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
import java.time.Duration;
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
        LocalTime startTime = LocalTime.of(11, 23);
        LocalTime endTime = LocalTime.of(11, 30);
        LocalDate startDate = LocalDate.of(2024, 8, 17);
        LocalDate endDate = LocalDate.of(2024, 8, 18);

        if ( startDate.equals(LocalDate.now()) || startDate.isBefore(LocalDate.now()) && endDate.isAfter(LocalDate.now())) {
            System.out.println("it currently start day checking time now");
            if ((startTime.equals(LocalTime.now()) || startTime.isBefore(LocalTime.now())) && endTime.isAfter(LocalTime.now())) {
                System.out.println("It is currently in start time");
            } else if (LocalTime.now().isBefore(startTime)) {
                System.out.println("It is not yet time");
            } else {
                System.out.println("It has passed the start time and passed the end time");
            }
        } else if (LocalDate.now().isBefore(endDate)) {
            System.out.println("it not yet date");
        }else {
            System.out.println("it has passed end date and passed the start");
        }


    long differenceInMinutes = Duration.between(startTime, endTime).toMinutes();

        System.out.println("Time difference: " + differenceInMinutes + " minutes");

    }

}
