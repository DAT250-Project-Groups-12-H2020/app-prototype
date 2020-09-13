package no.hvl.dat250.app

import no.hvl.dat250.app.model.Account
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import javax.persistence.Persistence
import kotlin.test.assertNotNull

/**
 * @author Elg
 */
internal class MainKtTest {


  @Test
  internal fun name() {
    val factory = Persistence.createEntityManagerFactory("app")
    val em = factory.createEntityManager()
    // read the existing entries and write to console
    assertNotNull(em.find(Account::class.java, "399bf50c-a066-4422-a4e3-3e2f4d4eed69"))
    em.close()
  }

  companion object {

    @JvmStatic
    @BeforeAll
    internal fun setUp() {

    }
  }
}
