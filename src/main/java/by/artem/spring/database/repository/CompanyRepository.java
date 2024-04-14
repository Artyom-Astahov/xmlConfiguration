package by.artem.spring.database.repository;

import by.artem.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(
            "select c from Company c " +
                    "join fetch c.locales cl " +
                    "where c.name = :name"
    )
    Optional<Company> findByName(String name);

    List<Company> findAllByNameContainingIgnoreCase(String fragment);


    @Modifying(clearAutomatically = true)
    @Query("update Company c set c.name = :name where c.id = :id")
    Integer updateCompanyNameById(Integer id, String name);

    void deleteCompanyByNameContaining(Character symbol);





}
