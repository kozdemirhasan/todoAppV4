package de.kozdemir.todoappv4.controller;

import de.kozdemir.todoappv4.model.Todo;
import de.kozdemir.todoappv4.model.TodoDto;
import de.kozdemir.todoappv4.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public String getAllTodos(Model model) {
        model.addAttribute("title", "Todos");
        model.addAttribute("ac", "todos");
        model.addAttribute("new", true);

        List<Todo> todos = todoService.findAll();
        List<TodoDto> newTodos = new TodoDto().convertorDateToString(todos); // Date to String format

        model.addAttribute("todoList", newTodos);
        if (newTodos.isEmpty())
            model.addAttribute("em", true);
        else
            model.addAttribute("em", false);

        return "todos";
    }

    @PostMapping("/todos")
    public String addTodo(String description, Model model) {
        Todo todo = new Todo();
//        todo.setId(id);
        todo.setDescription(description);
        todo.setCreatedDate(LocalDateTime.now()); //now
        todo.setModifiedDate(null); //Default Wert ist null
        todo.setComplete(false);

        // Validierung
        if (todo.getDescription().length() <= 5) {
            model.addAttribute("failed", true);
            model.addAttribute("todo", todo);
        } else {
            todoService.save(todo);
            model.addAttribute("saved", true);
        }
        return "redirect:/todos";
    }


    @PostMapping("/todos/change")
    public String changeStatus(Integer id) {
        Todo t = todoService.findById(id).get();
        if (t.getComplete()) {
            t.setComplete(false);
            t.setModifiedDate(null);
        } else {
            t.setComplete(true);
            t.setModifiedDate(LocalDateTime.now()); //modifierd time change
        }

        todoService.save(t);

        return "redirect:/todos";
    }

    @PostMapping("/todos/delete")
    public String delete(Integer id) {

        Todo todo = todoService.findById(id).get();
        todoService.delete(todo);

        return "redirect:/todos";
    }

    @GetMapping("/todos/edit")
    public String editGetForm(Integer id, Model model) {
        model.addAttribute("headline", "Todo Bearbeiten");
        model.addAttribute("ac", "todos");
        model.addAttribute("todo", todoService.findById(id));
        return "todo-edit";
    }


    @PostMapping("/todos/edit")
    public String editTodo(Integer id, String description, Model model) {
        Todo t = todoService.findById(id).get();
        t.setDescription(description);
        todoService.save(t);
        model.addAttribute("todo", todoService.findById(id));
        return "todo-edit";

    }



}
