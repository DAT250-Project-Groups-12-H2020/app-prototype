package no.hvl.dat250.app.repository.impl

import no.hvl.dat250.app.model.Poll
import no.hvl.dat250.app.model.Vote
import no.hvl.dat250.app.repository.PollRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

class PollRepositoryImpl(private val entityManager: EntityManager) : PollRepository,
  SimpleJpaRepository<Poll, Long>(Poll::class.java, entityManager) {

  override fun findAllVoters(id: Long): List<Vote> {
    return emptyList()
  }
}
