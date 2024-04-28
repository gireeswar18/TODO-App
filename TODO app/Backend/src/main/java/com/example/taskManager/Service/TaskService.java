package com.example.taskManager.Service;

import com.example.taskManager.Model.Task;
import com.example.taskManager.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TaskService {

    @Autowired
    TaskRepository taskRepository;


    public ResponseEntity<Task> addTask(Task task) {

        try {
            taskRepository.save(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<Task>> getTasks() {

        try {
            return new ResponseEntity<>(taskRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Task> updateTask(long id, Task updatedTask) {
        try {
            Task task = new Task();
            task.setId(id);
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
            taskRepository.save(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Task> deleteTask(long id) {

        try {
            Task task = new Task();
            task.setId(id);
            taskRepository.deleteById(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
