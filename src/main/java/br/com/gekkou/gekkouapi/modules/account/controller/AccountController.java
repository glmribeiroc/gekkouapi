package br.com.gekkou.gekkouapi.modules.account.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gekkou.gekkouapi.modules.account.controller.request.AddRoleToUserRequest;
import br.com.gekkou.gekkouapi.modules.account.entity.Account;
import br.com.gekkou.gekkouapi.modules.account.entity.Role;
import br.com.gekkou.gekkouapi.modules.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<Account>> getUsers() {
        return ResponseEntity.ok().body(accountService.getAccounts());
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
    }

    @PostMapping("/role/create")
    public ResponseEntity<Role> createAccount(@RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createRole(role));
    }

    @PostMapping("/addrole")
    public ResponseEntity<?> addRoleToUser(@RequestBody AddRoleToUserRequest request) {
        accountService.addRoleToUser(request.email(), request.roleName());
        return ResponseEntity.ok().build();
    }

}
