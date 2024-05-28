package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entities.Room;
import app.entities.Schedule;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findAll();
}