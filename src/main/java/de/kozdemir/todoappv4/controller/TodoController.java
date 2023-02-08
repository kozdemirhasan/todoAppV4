package de.kozdemir.todoappv4.controller;

import com.google.gson.Gson;
import de.kozdemir.todoappv4.model.Todo;
import de.kozdemir.todoappv4.model.TodoDto;
import de.kozdemir.todoappv4.repository.TodoRepository;
import de.kozdemir.todoappv4.services.LoginService;
import de.kozdemir.todoappv4.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("todos")
    public String getAllTodos(Model model) {
        if (loginService.isLoggedIn()) {
            model.addAttribute("title", "Todos");
            model.addAttribute("ac", "todos");
            model.addAttribute("new", true);

            List<Todo> todos = todoService.findAll();
            List<TodoDto> newTodos = new TodoDto().convertorDateToString(todos); // Date to String format

            model.addAttribute("todoList", newTodos);
            model.addAttribute("emailName", loginService.getUser().getEmail());
            if (newTodos.isEmpty())
                model.addAttribute("em", true);
            else
                model.addAttribute("em", false);

            return "todos";
        }
        return "login-form";
    }

    @GetMapping("todos/new")
    public String newForm(Todo todo, Model model) {
        model.addAttribute("title", "Add new ToDo");
        return "todo-form";
    }

    @PostMapping("todos/add")
    public String newTodoAdd(@Valid Todo todo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "todo-form";
        }

        todo.setId(todo.getId());
        todo.setDescription(todo.getDescription());
        todo.setCreatedDate(LocalDateTime.now()); //now
        todo.setModifiedDate(null); //Default Wert ist null
        todo.setComplete(false);

        todoService.save(todo);

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
        return "todo-form";
    }


    @PostMapping("/todos/edit")
    public String editTodo(Integer id, String description, Model model) {
        Todo t = todoService.findById(id).get();
        t.setDescription(description);
        todoService.save(t);
        model.addAttribute("todo", todoService.findById(id));
        return "todos";

    }

    @GetMapping("json")
    public String jsonExport(Model model) {
        Gson gson = new Gson();
        try {
            String str = gson.toJson(todoService.findAll());
            model.addAttribute("jsonTodos", str );
        }catch (Exception e){
            System.out.println("Hata olustu...");
            e.printStackTrace();
        }



        return "json";
    }



    @GetMapping("search/{s}")
    public String search(String s, Model model) {
        model.addAttribute("title", "Add new ToDo");
        todoService.search(s);
        return "todos";
    }

}
