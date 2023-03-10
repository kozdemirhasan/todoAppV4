package de.kozdemir.todoappv4.controller;

import de.kozdemir.todoappv4.model.Todo;
import de.kozdemir.todoappv4.model.TodoDto;
import de.kozdemir.todoappv4.services.LoginService;
import de.kozdemir.todoappv4.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private LoginService loginService;


    @GetMapping("")
    public String getAllTodos(String keyword, Model model) {

        if (loginService.isLoggedIn()) {
            model.addAttribute("title", "Todos App");
            model.addAttribute("ac", "todos");
            model.addAttribute("new", true);

            List<Todo> todos;
            if (keyword == null) {
//                todos = todoService.findAll(); //alt version.
                todos = todoService.findByAllWithUserId(loginService.getUser().getId());
            } else {
                model.addAttribute("keyword", keyword);
                todos = todoService.search(keyword);
            }
            List<TodoDto> newTodos = new TodoDto().convertorDateToString(todos); // Date to String format
            model.addAttribute("todoList", newTodos);

            model.addAttribute("emailName", loginService.getUser().getEmail());
            if (todos.isEmpty())
                model.addAttribute("em", true);
            else
                model.addAttribute("em", false);

            return "todos";
        }
        return "login-form";
    }

    @GetMapping("new")
    public String newForm(Todo todo, Model model) {
        model.addAttribute("title", "Add new ToDo");
        return "todo-form";
    }

    @PostMapping("add")
    public String newTodo(@Valid Todo todo, BindingResult result, Model model) {
//        model.addAttribute("todo", new Todo());
        if (result.hasErrors()) {
            return "todo-form";
        }
//        todo.setId(todo.getId());
        todo.setDescription(todo.getDescription());

        try {
            todo.setCreatedDate(todo.getCreatedDate()); //now
        } catch (NullPointerException e) {
            todo.setCreatedDate(null);
        }

        todo.setModifiedDate(null); //Default Wert ist null
        todo.setComplete(false);

        todo.setUser(loginService.getUser()); // ****

        todoService.save(todo);

        return "redirect:/todos";
    }


    @PostMapping("change")
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

    @PostMapping("delete")
    public String delete(Integer id) {
        Todo todo = todoService.findById(id).get();
        todoService.delete(todo);
        return "redirect:/todos";
    }

    @GetMapping("edit")
    public String editGetForm(Integer id, Model model) {
        model.addAttribute("headline", "Todo Bearbeiten");
        model.addAttribute("ac", "todos");
        model.addAttribute("todo", todoService.findById(id).get());
        model.addAttribute("title", "Edit todo");
        return "todo-form";
    }


    @PostMapping("edit")
    public String editTodo(Integer id, String description, Model model) {
        Todo t = todoService.findById(id).get();
        t.setDescription(description);
        todoService.save(t);
        model.addAttribute("todo", todoService.findById(id));
        return "todos";

    }

    @GetMapping("json")
    public String jsonImport(Model model) {

//        List<Todo> todos = todoService.findAll();
        List<Todo> todos = todoService.findByAllWithUserId(loginService.getUser().getId());
        model.addAttribute("jsonTodos", todos);

        return "json";
    }


}
