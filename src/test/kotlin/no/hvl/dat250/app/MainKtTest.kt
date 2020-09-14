package no.hvl.dat250.app

import no.hvl.dat250.app.dto.AccountRequest
import no.hvl.dat250.app.dto.toAccount
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.persistence.EntityManager
import javax.persistence.Query
import kotlin.test.assertTrue


/**
 * @author Elg
 */
internal class MainKtTest {


  @BeforeEach
  internal fun setUp() {
    val entityManager: EntityManager = factory.createEntityManager()
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
    entityManager.close()
  }

  @Test
  internal fun `admin account exists`() {
    val entityManager: EntityManager = factory.createEntityManager()
    // read the existing entries and write to console

    val q: Query = entityManager.createQuery("select a from Account a where a.admin = true")
    assertTrue { q.resultList.isNotEmpty() }
  }

  @Test
  internal fun `There exists at least one normal account`() {
    val entityManager: EntityManager = factory.createEntityManager()
    // read the existing entries and write to console

    val q: Query = entityManager.createQuery("select a from Account a where a.admin = false")
    assertTrue { q.resultList.isNotEmpty() }
  }
}
