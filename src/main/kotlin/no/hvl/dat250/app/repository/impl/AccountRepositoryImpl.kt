package no.hvl.dat250.app.repository.impl

import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.repository.AccountRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

class AccountRepositoryImpl(private val entityManager: EntityManager) : AccountRepository,
  SimpleJpaRepository<Account, String>(Account::class.java, entityManager) {


  override fun findByAdmin(admin: Boolean): List<Account> {
    val query =
      entityManager.createQuery("select a from Account a where a.admin = :admin", Account::class.java)
    query.setParameter("admin", admin)
    return query.resultList
  }

  override fun findByName(name: String): List<Account> {
    val query =
      entityManager.createQuery("select a from Account a where a.name = :name", Account::class.java)
    query.setParameter("name", name)
    return query.resultList
  }

  override fun findByEmail(email: String): Account? {
    val query =
      entityManager.createQuery("select a from Account a where a.email = :email", Account::class.java)
    query.setParameter("email", email)
    return query.singleResult
  }
}
