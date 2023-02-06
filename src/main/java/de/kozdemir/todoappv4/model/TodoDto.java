package de.kozdemir.todoappv4.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TodoDto {

    private int id;

    private String description;

    private Boolean complete;

    private String createdDate;

    private String modifiedDate;

    public TodoDto() {
    }

    public TodoDto(int id, String description, Boolean complete, String createdDate, String modifiedDate) {
        this.id = id;
        this.description = description;
        this.complete = complete;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public List<TodoDto> convertorDateToString(List<Todo> altTodos) {
        List<TodoDto> newTodos = new ArrayList<>();
        TodoDto todoDto;
        for (Todo t : altTodos) {
            todoDto = new TodoDto();
            todoDto.setId(t.getId());
            todoDto.setDescription(t.getDescription());
            todoDto.setCreatedDate(t.getCreatedDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))); // Date to String
            try {
                todoDto.setModifiedDate(t.getModifiedDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))); // Date to String
            } catch (NullPointerException e) {
                todoDto.setModifiedDate("");
            }
            todoDto.setComplete(t.getComplete());
            newTodos.add(todoDto);
        }
        return newTodos;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
