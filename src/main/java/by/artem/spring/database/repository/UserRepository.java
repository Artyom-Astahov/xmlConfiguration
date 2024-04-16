package by.artem.spring.database.repository;


import by.artem.spring.database.entity.Role;
import by.artem.spring.database.entity.User;

import by.artem.spring.dto.IPersonalInfo;

import by.artem.spring.dto.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        FilterUserRepository, QuerydslPredicateExecutor<User> {

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

    List<User> findByRoleAndBirthDateBetween(Role role, LocalDate start, LocalDate end);

    @Query(nativeQuery = true, value = "SELECT * FROM users u limit 4")
    List<User> findFirst4By();

    @Query("select u from User u")
    List<User> findAllBy(Sort sort);

    @Query(
            "select u from User u " +
                    "where u.role = ?1"
    )
    List<User> findAllByRole(Role role, Pageable pageable);


}
