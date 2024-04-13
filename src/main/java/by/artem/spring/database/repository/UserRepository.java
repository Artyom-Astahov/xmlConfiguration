package by.artem.spring.database.repository;

import by.artem.spring.database.entity.Company;
import by.artem.spring.database.entity.Role;
import by.artem.spring.database.entity.User;
import by.artem.spring.database.repository.bpp.InjectBean;
import by.artem.spring.database.repository.pool.ConnectionPool;
import by.artem.spring.dto.IPersonalInfo;
import by.artem.spring.service.PersonalInfo;
import by.artem.spring.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findFirstBy(Sort sort);
    Page<User> findAllBy(Pageable pageable);

    @Query(
            "select u from User u " +
            "where u.firstname like %:firstname% " +
            "and u.lastname like %:lastname%"
    )
    List<User> findAllByFirstnameContainingAndLastnameContaining(String firstname, String lastname);


    @Query(value = "select u.firstname, u.lastname,  u.birth_date from users u " +
            "where company_id = :companyId",
    nativeQuery = true)
    List<IPersonalInfo> findAllByCompanyId(Integer companyId);

    List<User> findByRoleAndBirthDateBetween(Role role , LocalDate start, LocalDate end);


}
