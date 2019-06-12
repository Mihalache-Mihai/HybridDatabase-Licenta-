package com.licenta.repository;

import com.licenta.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
        //@Query(value="SELECT u from Medicine u where u.name = ?1 ")
        List<Medicine> findByNameContaining (String name);

        Medicine getMedicineById(long id);

        List<Medicine> findAllByCompany_CUIOrderByNameAsc(String CUI);
}
