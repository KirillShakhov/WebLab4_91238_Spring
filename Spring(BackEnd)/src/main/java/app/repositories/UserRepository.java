package app.repositories;

import app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Просто интерфейс
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Поиск по Username
    User findOneByUsername(String username);
    //Поиск по Токену
    User findByAuthTokenEquals(String token);
}
