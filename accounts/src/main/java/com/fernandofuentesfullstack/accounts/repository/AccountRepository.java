package com.fernandofuentesfullstack.accounts.repository;

import com.fernandofuentesfullstack.accounts.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByCustomerId(int customerId);
}
