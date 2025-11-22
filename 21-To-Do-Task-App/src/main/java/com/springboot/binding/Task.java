package com.springboot.binding;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    @NotBlank(message = "Please enter a task name.")
    private String name;
    
    @NotBlank(message = "Please enter task description")
    private String description;

    @NotNull(message = "Please select a task date.")
    @FutureOrPresent(message = "Task date cannot be in the past.")
    private LocalDate date;

    @NotNull(message = "Please select a task time.")
    private LocalTime timings;
    
    private String status;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimings() {
        return timings;
    }

    public void setTimings(LocalTime timings) {
        this.timings = timings;
    }

    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return "Task [taskId=" + taskId + ", name=" + name + ", date=" + date + ", timings=" + timings + "]";
    }
}
