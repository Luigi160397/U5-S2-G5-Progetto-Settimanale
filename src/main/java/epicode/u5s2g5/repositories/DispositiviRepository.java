package epicode.u5s2g5.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.u5s2g5.entities.Dispositivo;

@Repository
public interface DispositiviRepository extends JpaRepository<Dispositivo, UUID> {

}
