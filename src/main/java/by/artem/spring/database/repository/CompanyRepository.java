package by.artem.spring.database.repository;

import by.artem.spring.database.entity.Company;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CompanyRepository {

    public Optional<Company> findById(Integer id) {
        System.out.println("CompanyRepository findById method");
        return Optional.of(new Company(id));
    }

    public Optional<Company> update(Integer id) {
        System.out.println("CompanyRepository update method");
        return Optional.of(new Company(id));
    }

    public Optional<Company> create(Integer id) {
        System.out.println("CompanyRepository create method");
        return Optional.of(new Company(id));
    }

    public void delete(Integer id) {
        System.out.println("CompanyRepository delete method");

    }
}
