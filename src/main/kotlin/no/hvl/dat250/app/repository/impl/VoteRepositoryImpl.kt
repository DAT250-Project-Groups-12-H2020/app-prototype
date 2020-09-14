package no.hvl.dat250.app.repository.impl

import no.hvl.dat250.app.model.Vote
import no.hvl.dat250.app.repository.VoteRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

class VoteRepositoryImpl(private val entityManager: EntityManager) : VoteRepository,
  SimpleJpaRepository<Vote, Long?>(Vote::class.java, entityManager) {


}
