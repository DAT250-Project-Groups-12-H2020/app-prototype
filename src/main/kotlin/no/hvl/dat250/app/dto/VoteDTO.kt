package no.hvl.dat250.app.dto

import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.model.Vote
import java.time.OffsetDateTime


data class VoteRequest(
  val id: Long? = null,
  val yesVotes: Int? = null,
  val noVotes: Int? = null,
  val castTime: OffsetDateTime? = null,
  val account: Account? = null
)

data class VoteResponse(
  val id: Long,
  val yesVotes: Int,
  val noVotes: Int,
  val castTime: OffsetDateTime,
  val account: Account?
)

fun VoteRequest.toVote(): Vote {
  val vote = Vote()
  vote.id = id ?: -1
  vote.yesVotes = yesVotes ?: 0
  vote.noVotes = noVotes ?: 0
  vote.castTime = castTime ?: OffsetDateTime.now()
  vote.account = account
  return vote
}

fun Vote.toResponse(): VoteResponse {
  return VoteResponse(id, yesVotes, noVotes, castTime, account)
}
