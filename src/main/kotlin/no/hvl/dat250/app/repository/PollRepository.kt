package no.hvl.dat250.app.repository

import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.model.Poll
import org.springframework.data.repository.CrudRepository

interface PollRepository : CrudRepository<Poll, Long?> {

  fun findAllVoters(id: Long): List<Account>

}
