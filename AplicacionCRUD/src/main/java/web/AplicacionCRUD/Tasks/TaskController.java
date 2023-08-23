package web.AplicacionCRUD.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/tasks")
@Service
public class TaskController {

        private final TaskService taskService;

        @Autowired
        public TaskController(TaskService taskService) {
            this.taskService = taskService;
        }

        @GetMapping
        public List<TaskAplication> getAllTask() {
            return taskService.getTasks();
        }

        @PostMapping
        public ResponseEntity<Object> crearTask(@RequestBody TaskAplication task) {
//            taskService.newTask(task);
//            return ResponseEntity.ok("Tarea registrada");
            return taskService.createTask(task);
        }

    @PutMapping
    public ResponseEntity<Object> actualizarTask(@RequestBody TaskAplication task) {
        return TaskService.updateTask(task);
    }

    @DeleteMapping(path = "{taskId}")
    public ResponseEntity<Object> eliminarTask(@PathVariable("taskId") Long taskId) {
        return TaskService.deleteTask(taskId);
    }

}
