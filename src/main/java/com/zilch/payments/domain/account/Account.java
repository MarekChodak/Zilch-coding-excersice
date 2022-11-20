package com.zilch.payments.domain.account;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.zilch.payments.domain.account.AccountStatus.BLACKLISTED;

@Builder
@Getter
@EqualsAndHashCode
@Document("accounts")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    @Id
    private AccountId id;

    private AccountStatus status;

    private PersonDetails personDetails;

    @Version
    private Long version;

    public static Account from(AccountCreationCommand accountCreationCommand) {
        return Account.builder()
                .id(AccountId.unique())
                .status(accountCreationCommand.accountStatus())
                .personDetails(accountCreationCommand.personDetails())
                .build();
    }

    public boolean isPurchaseAllowed() {
        return !status.equals(BLACKLISTED);
    }
}
