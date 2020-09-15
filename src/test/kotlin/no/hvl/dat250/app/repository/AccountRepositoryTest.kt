package no.hvl.dat250.app.repository

import no.hvl.dat250.app.TEST_PERSISTENCE_UNIT_NAME
import no.hvl.dat250.app.dto.AccountRequest
import no.hvl.dat250.app.dto.toAccount
import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.repository.impl.AccountRepositoryImpl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.persistence.EntityManager
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class AccountRepositoryTest {

  lateinit var entityManager: EntityManager
  lateinit var accountRepo: AccountRepository

  @BeforeEach
  internal fun setUp() {
    entityManager = Persistence.createEntityManagerFactory(TEST_PERSISTENCE_UNIT_NAME).createEntityManager()
    accountRepo = AccountRepositoryImpl(entityManager)

    if (accountRepo.count() == 0L) {

      val accounts = ArrayList<Account>()
      for (i in 0..50) {

        accounts += AccountRequest(
          name = "test user",
          email = "test-$i@test.net",
          password = "password"
        ).toAccount()

      }
      accounts +=
        AccountRequest(
          admin = true,
          name = "admin",
          email = "admin@app.net",
          password = "admin"
        ).toAccount()

      entityManager.transaction.begin()
      accountRepo.saveAll(accounts)
      entityManager.transaction.commit()
    }
  }

  @AfterEach
  internal fun tearDown() {
    entityManager.close()
  }

  @Test
  internal fun `admin account exists`() {
    val accounts = accountRepo.findByAdmin(true)
    assertTrue { accounts.isNotEmpty() }
    for (account in accounts) {
      assertTrue(account.admin)
    }
  }

  @Test
  internal fun `there exists at least one normal account`() {
    val accounts = accountRepo.findByAdmin(false)
    assertTrue { accounts.isNotEmpty() }
    for (account in accounts) {
      assertFalse(account.admin)
    }
  }

  @Test
  internal fun `the set of admin and non-admins results in all users`() {
    val accounts = accountRepo.findByAdmin(true).toHashSet()
    accounts.addAll(accountRepo.findByAdmin(false))
    val allAccounts = HashSet<Account>()
    allAccounts.addAll(accountRepo.findAll())
    assertEquals(allAccounts, accounts)
  }


  @Test
  internal fun `findByName return all with the same name`() {
    assertEquals(accountRepo.findByAdmin(false), accountRepo.findByName("test user"))
  }
}
