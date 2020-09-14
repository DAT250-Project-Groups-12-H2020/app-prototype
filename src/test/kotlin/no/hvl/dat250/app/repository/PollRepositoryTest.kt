package no.hvl.dat250.app.repository

import no.hvl.dat250.app.dto.*
import no.hvl.dat250.app.factory
import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.repository.impl.AccountRepositoryImpl
import no.hvl.dat250.app.repository.impl.PollRepositoryImpl
import no.hvl.dat250.app.repository.impl.VoteRepositoryImpl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.persistence.EntityManager
import kotlin.test.assertNotNull

internal class PollRepositoryTest {

  lateinit var entityManager: EntityManager
  lateinit var accountRepo: AccountRepository
  lateinit var pollRepo: PollRepository
  lateinit var voteRepo: VoteRepository

  @BeforeEach
  internal fun setUp() {
    entityManager = factory.createEntityManager()
    accountRepo = AccountRepositoryImpl(entityManager)
    pollRepo = PollRepositoryImpl(entityManager)
    voteRepo = VoteRepositoryImpl(entityManager)

    if (accountRepo.count() == 0L) {

      val accounts = ArrayList<Account>()
      for (i in 0..5) {

        accounts += AccountRequest(
          name = "test user $i",
          email = "test-$i@test.net",
          password = "password"
        ).toAccount()

      }
      accounts +=
        AccountRequest(
          admin = true,
          name = "admin",
          email = ADMIN_EMAIL,
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
  internal fun `findAllVoters works`() {
    var account = assertNotNull(accountRepo.findByEmail(ADMIN_EMAIL))

    entityManager.transaction.begin()
    val poll = PollRequest().toPoll()
    val vote = voteRepo.save(VoteRequest(yesVotes = 1, noVotes = 2).toVote())
    poll.votes.add(vote)
    account.polls.add(poll)
    account.votes[poll] = vote

    pollRepo.save(poll)
    account = accountRepo.save(account)
    entityManager.transaction.commit()

    assertEquals(listOf(account), pollRepo.findAllVoters(poll.id!!))
  }

  companion object {
    private const val ADMIN_EMAIL = "admin@app.net"
  }
}
