package no.hvl.dat250.app

import no.hvl.dat250.app.model.Account
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
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
      val regex = "[\r\n]".toRegex()
      for (queryString in File("src/test/resources/test_data.sql").readText().replace(regex, "").split(';')) {
        entityManager.createNativeQuery(queryString)
      }
    }
    entityManager.transaction.commit()
    entityManager.close()
  }

  @Test
  internal fun name() {
    val entityManager: EntityManager = factory.createEntityManager()
    // read the existing entries and write to console
    assertNotNull(entityManager.find(Account::class.java, "399bf50c-a066-4422-a4e3-3e2f4d4eed69"))
  }
}
