package web.AplicacionCRUD.Tasks;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping(path = "api/tasks")

@Service
public class TaskService {
    private static TaskRepository taskRepository = null;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskAplication> getTasks(){
        return this.taskRepository.findAll();
    }


//
//    @GetMapping
//    public List<TaskAplication> getTasks() {
//        return taskRepository.findAll();
//    }

//    public void newTask(TaskAplication task) {
//        Optional<TaskAplication> existingTask = taskRepository.findTaskAplicationByName(task.getName());
//
//        HashMap<String, Object> datos = new HashMap<String, Object>();
//
//        if(existingTask.isPresent()){
//            datos.put("data", true);
//            datos.put("message", "La Tarea ya existe");
//            return new ResponseEntity<>(
//                    datos,
//                    HttpStatus.CONFLICT
//            );
//
//
////            throw new IllegalStateException("La Tarea ya existe");
//        }
//        taskRepository.save(task);
//        datos.put("data", task);
//        datos.put("message", "Tarea creado");
//        return new ResponseEntity<>(datos,
//                HttpStatus.CREATED
//        );
//
////        taskRepository.save(task);
//    }

    public ResponseEntity<Object> createTask(TaskAplication task) {
        Optional<TaskAplication> existingTask = taskRepository.findTaskAplicationByName(task.getName());
        HashMap<String, Object> datos = new HashMap<>();

        if (existingTask.isPresent()) {
            datos.put("data", false);
            datos.put("message", "La Tarea ya existe");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        } else {
            TaskAplication savedTask = taskRepository.save(task);
            datos.put("data", savedTask);
            datos.put("message", "Tarea creada");

            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }
    }

    public static ResponseEntity<Object> updateTask(TaskAplication task) {
        Optional<TaskAplication> existingTask = taskRepository.findById(task.getId());
        HashMap<String, Object> datos = new HashMap<>();

        if (existingTask.isPresent()) {
            TaskAplication savedTask = taskRepository.save(task);
            datos.put("data", savedTask);
            datos.put("message", "Tarea actualizada");

            return new ResponseEntity<>(datos, HttpStatus.OK);
        } else {
            datos.put("data", false);
            datos.put("message", "La tarea con ID " + task.getId() + " no existe");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }
    }

    public static ResponseEntity<Object> deleteTask(Long taskId) {
        boolean exists = taskRepository.existsById(taskId);
        HashMap<String, Object> datos = new HashMap<>();

        if (!exists) {
            datos.put("data", false);
            datos.put("message", "La tarea con ID " + taskId + " no existe");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }

        taskRepository.deleteById(taskId);
        datos.put("data", true);
        datos.put("message", "La tarea con ID " + taskId + " ha sido eliminada");
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }
}
