package br.com.gekkou.gekkouapi.modules.account.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gekkou.gekkouapi.modules.account.entity.Account;
import br.com.gekkou.gekkouapi.modules.account.entity.Role;
import br.com.gekkou.gekkouapi.modules.account.repository.AccountRepository;
import br.com.gekkou.gekkouapi.modules.account.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(account.getEmail(), account.getPassword(), authorities);
    }

    public Account createAccount(Account account) {
        log.info("Creating new account {} to the database", account.getName());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Role createRole(Role role) {
        log.info("Creating new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}", roleName, email);
        Account account = accountRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        account.getRoles().add(role);
    }

    public Account getAccount(String email) {
        log.info("Fetching user {}", email);
        return accountRepository.findByEmail(email);
    }

    public List<Account> getAccounts() {
        log.info("Fetching all users");
        return accountRepository.findAll();
    }

}
