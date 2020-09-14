package no.hvl.dat250.app.repository

import no.hvl.dat250.app.model.Vote
import org.springframework.data.repository.CrudRepository

interface VoteRepository : CrudRepository<Vote, Long> {
}
