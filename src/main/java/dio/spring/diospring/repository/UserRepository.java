package dio.spring.diospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dio.spring.diospring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query("Select e From User e Join Fetch e.roles Where e.username = (:username)")
    public User findByUsername(@Param ("username") String username);
}
