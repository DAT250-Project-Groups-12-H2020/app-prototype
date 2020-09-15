package no.hvl.dat250.app.dto

import no.hvl.dat250.app.model.Vote
import java.time.OffsetDateTime


data class VoteRequest(
  val id: Long? = null,
  val firstVotes: Int? = null,
  val secondVotes: Int? = null,
  val castTime: OffsetDateTime? = null,
)

data class VoteResponse(
  val id: Long,
  val firstVotes: Int,
  val secondVotes: Int,
  val castTime: OffsetDateTime,
)

fun VoteRequest.toVote(): Vote {
  val vote = Vote()
  vote.id = id
  vote.firstVotes = firstVotes ?: 0
  vote.secondVotes = secondVotes ?: 0
  vote.castTime = castTime ?: OffsetDateTime.now()
  return vote
}

fun Vote.toResponse(): VoteResponse {
  return VoteResponse(id!!, firstVotes, secondVotes, castTime)
}
