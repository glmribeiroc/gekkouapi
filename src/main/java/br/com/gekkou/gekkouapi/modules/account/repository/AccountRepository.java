package br.com.gekkou.gekkouapi.modules.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gekkou.gekkouapi.modules.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}
