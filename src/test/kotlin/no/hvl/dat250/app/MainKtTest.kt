package no.hvl.dat250.app

import no.hvl.dat250.app.dao.AccountRequest
import no.hvl.dat250.app.dao.toAccount
import no.hvl.dat250.app.model.Account
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence
import javax.persistence.Query
import kotlin.test.assertNotNull


/**
 * @author Elg
 */
internal class MainKtTest {

  private lateinit var factory: EntityManagerFactory

  @BeforeEach
  internal fun setUp() {
    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
    val entityManager: EntityManager = factory.createEntityManager()
    entityManager.transaction.begin()
    val q: Query = entityManager.createQuery("select a from Account a")
    if (q.resultList.isEmpty()) {

      for (i in 0..100) {
        entityManager.persist(
          AccountRequest(
            false,
            emptyList(),
            "test user $i",
            "test-$i@test.net",
            "password"
          ).toAccount()
        )
      }
      entityManager.persist(
        AccountRequest(
          true,
          emptyList(),
          "ammin user",
          "admin@app.net",
          "admin"
        ).toAccount()
      )
    }
    entityManager.transaction.commit()
    entityManager.close()
  }

  @Test
  internal fun name() {
    val entityManager: EntityManager = factory.createEntityManager()
    // read the existing entries and write to console
    assertNotNull(entityManager.find(Account::class.java, "817ce778-bbd4-4bb0-857d-bba5b60602cf"))

  }
}
