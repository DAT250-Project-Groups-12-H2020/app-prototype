package no.hvl.dat250.app.repository

import no.hvl.dat250.app.model.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, String> {

  fun findByAdmin(admin: Boolean): List<Account>

  fun findByName(name: String): List<Account>

  fun findByEmail(email: String): Account?

}
