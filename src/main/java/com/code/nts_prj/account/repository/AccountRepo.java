package com.code.nts_prj.account.repository;

import com.code.nts_prj.account.entity.AccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity, Long> {
	Optional<AccountEntity> findByUserName(String userName);
}
