import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Task } from './task.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
 
  private apiUrl = "http://localhost:8080/todo";

  constructor(private httpClient : HttpClient) {}

  createTask (newTask : Task) : Observable<Task>
  {
    return this.httpClient.post<Task>(`${this.apiUrl}/addTask`, newTask);
  }

  getTasks () : Observable<Task[]>
  {
    return this.httpClient.get<Task[]>(`${this.apiUrl}`);
  }

  updateTask (id : number, task : Task) : Observable<Task>
  {
    return this.httpClient.put<Task>(`${this.apiUrl}/updateTask/${id}`, task);
  }

  deleteTask (id : number) : Observable<Task>
  {
    return this.httpClient.delete<Task>(`${this.apiUrl}/deleteTask/${id}`);
  }

}
