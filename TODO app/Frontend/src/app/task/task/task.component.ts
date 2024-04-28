import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service';
import { Task } from '../task.model';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css'],
})
export class TaskComponent implements OnInit {

  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
    this.getTasks();
  }
  // Creating a task
  newTask: Task = { description: "", completed: false };

  createTask(): void {
    this.taskService.createTask(this.newTask).subscribe((createdTask) => {
      this.newTask = { description: "", completed: false };
      this.taskList.push(createdTask);
    })
  }

  // Getting all tasks

  taskList: Task[] = [];

  getTasks(): void {
    this.taskService.getTasks().subscribe((tasks) => {
      this.taskList = tasks;
    })
  }

  // update task
  editedTask: Task | null = null;
  updatedTask: Task = { "description": "", "completed": false };

  editTask(task: Task): void {
    this.editedTask = task;
    this.updatedTask = { ...task };
  }

  updateTask(): void {
    if (this.editedTask) {
      this.taskService.updateTask(this.editedTask.id!, this.updatedTask)
        .subscribe((responseTask) => {
          const index = this.taskList.findIndex((responseTask) => responseTask.id == this.editedTask!.id);
          if (index >= 0) {
            this.taskList[index] = responseTask;
            this.cancelEdit();
          }
        });
    }
  }

  cancelEdit(): void {
    this.editedTask = null;
    this.updatedTask = { "description": "", "completed": false };
  }

  // Delete Task

  deleteTask(id: number): void {
    if (confirm("Are you sure want to delete this task?")) {
      this.taskService.deleteTask(id)
        .subscribe((deletedTask) => {
          this.taskList = this.taskList.filter((task) => task.id !== id);
        })
    }
  }

}
