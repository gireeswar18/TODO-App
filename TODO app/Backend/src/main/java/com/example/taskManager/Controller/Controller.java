package com.example.taskManager.Controller;

import com.example.taskManager.Model.Task;
import com.example.taskManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todo")
@CrossOrigin

public class Controller {

    @Autowired
    TaskService taskService;

    @GetMapping("/helloWorld")
    public String helloWorld ()
    {
        return "Hello World!";
    }

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask (@RequestBody Task task)
    {
        return taskService.addTask(task);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks ()
    {
        return taskService.getTasks();
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Task> updateTask (@PathVariable("id") long id, @RequestBody Task task)
    {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Task> deleteTask (@PathVariable long id)
    {
        return taskService.deleteTask(id);
    }

}
