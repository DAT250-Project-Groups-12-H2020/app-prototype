package no.hvl.dat250.app.repository

import no.hvl.dat250.app.model.Poll
import no.hvl.dat250.app.model.Vote
import org.springframework.data.repository.CrudRepository

interface PollRepository : CrudRepository<Poll, Long> {

  fun findAllVoters(id: Long): List<Vote>

}
