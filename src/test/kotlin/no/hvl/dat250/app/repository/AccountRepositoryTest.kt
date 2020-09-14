package no.hvl.dat250.app.repository

import no.hvl.dat250.app.dto.AccountRequest
import no.hvl.dat250.app.dto.toAccount
import no.hvl.dat250.app.factory
import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.repository.impl.AccountRepositoryImpl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.persistence.EntityManager
import javax.persistence.Query
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class AccountRepositoryTest {

  lateinit var entityManager: EntityManager
  lateinit var accountRepo: AccountRepository

  @BeforeEach
  internal fun setUp() {
    entityManager = factory.createEntityManager()
    accountRepo = AccountRepositoryImpl(entityManager)

    entityManager.transaction.begin()
    val q: Query = entityManager.createQuery("select a from Account a")
    if (q.resultList.isEmpty()) {

      for (i in 0..100) {
        entityManager.persist(
          AccountRequest(
            name = "test user $i",
            email = "test-$i@test.net",
            password = "password"
          ).toAccount()
        )
      }
      entityManager.persist(
        AccountRequest(
          admin = true,
          name = "admin",
          email = "admin@app.net",
          password = "admin"
        ).toAccount()
      )
    }
    entityManager.transaction.commit()
  }

  @AfterEach
  internal fun tearDown() {
    entityManager.close()
  }

  @Test
  internal fun `admin account exists`() {
    val accounts = accountRepo.findAllAdmins()
    assertTrue { accounts.isNotEmpty() }
    for (account in accounts) {
      assertTrue(account.admin)
    }
  }

  @Test
  internal fun `There exists at least one normal account`() {
    val accounts = accountRepo.findAllNonAdmins()
    assertTrue { accounts.isNotEmpty() }
    for (account in accounts) {
      assertFalse(account.admin)
    }
  }

  @Test
  internal fun `Admin plus non-admins results in all users`() {
    val accounts = accountRepo.findAllNonAdmins().toHashSet()
    accounts.addAll(accountRepo.findAllAdmins())
    val allAccounts = HashSet<Account>()
    allAccounts.addAll(accountRepo.findAll())
    assertEquals(allAccounts, accounts)
  }
}
