package com.arisan.online.groupservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arisan.online.groupservice.domain.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long>{
	
	 Optional<Group> findByNamaGroup(String namaGroup);

}
