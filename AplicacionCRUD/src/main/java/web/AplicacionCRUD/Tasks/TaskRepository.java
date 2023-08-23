package web.AplicacionCRUD.Tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskAplication, Long> {

    Optional<TaskAplication> findTaskAplicationByName(String name);

}