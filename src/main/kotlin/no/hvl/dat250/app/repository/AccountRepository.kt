package no.hvl.dat250.app.repository

import no.hvl.dat250.app.model.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, String> {

  fun findAllAdmins(): List<Account>
  
  fun findAllNonAdmins(): List<Account>

}
