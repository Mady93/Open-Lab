package it.backend.spring.data;

import it.backend.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepo extends JpaRepository<Reservation, Integer>, CustomReservationRepo {

    @Query(value="select d from Reservation d join fetch d.cruise where d.cruise.id=:id",
            countQuery = "select count(d) from Reservation d join d.cruise where d.cruise.id=:id")
    public Page<Reservation> getReservations(Pageable pageable, Integer id);
}
