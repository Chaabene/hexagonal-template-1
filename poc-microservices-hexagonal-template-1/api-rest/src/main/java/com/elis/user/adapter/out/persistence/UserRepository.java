package com.elis.user.adapter.out.persistence;

import com.elis.user.adapter.out.persistence.model.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
}
