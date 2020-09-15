package no.hvl.dat250.app.repository

import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.model.Role
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, String> {

  fun findByRole(role: Role): List<Account>

  fun findByName(name: String): List<Account>

  fun findByEmail(email: String): Account?

}
