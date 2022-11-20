package com.zilch.payments.boundary.outbound.mongo.account;

import com.zilch.payments.domain.account.Account;
import com.zilch.payments.domain.account.AccountId;
import com.zilch.payments.domain.account.AccountRepository;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoAccountsRepository extends MongoRepository<Account, AccountId>, AccountRepository {

}
