package no.hvl.dat250.app.repository.impl

import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.repository.AccountRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

class AccountRepositoryImpl(private val entityManager: EntityManager) : AccountRepository,
  SimpleJpaRepository<Account, String>(Account::class.java, entityManager) {


  override fun findAllAdmins(): List<Account> {
    val query =
      entityManager.createQuery("select a from Account a where a.admin = true", Account::class.java)
    return query.resultList
  }

  override fun findAllNonAdmins(): List<Account> {
    val query =
      entityManager.createQuery("select a from Account a where a.admin = false", Account::class.java)
    return query.resultList
  }
}
