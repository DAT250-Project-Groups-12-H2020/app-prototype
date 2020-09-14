package no.hvl.dat250.app.repository.impl

import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.model.Poll
import no.hvl.dat250.app.repository.AccountRepository
import no.hvl.dat250.app.repository.PollRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

class PollRepositoryImpl(private val entityManager: EntityManager) : PollRepository,
  SimpleJpaRepository<Poll, Long?>(Poll::class.java, entityManager) {

  private val accountRepository: AccountRepository = AccountRepositoryImpl(entityManager)

  override fun findAllVoters(id: Long): List<Account> {
    val optionalPoll = findById(id)
    if (optionalPoll.isEmpty) return emptyList()
    val poll = optionalPoll.get()

    val voters = ArrayList<Account>()

    for (account in accountRepository.findAll()) {
      //account.votes.containsKey(poll) does not work!?!?
      if (account.votes.keys.any { it == poll }) {
        voters.add(account)
      }
    }
    return voters
  }
}
