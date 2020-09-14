package no.hvl.dat250.app.dto

import no.hvl.dat250.app.model.Poll
import java.time.OffsetDateTime

data class PollRequest(
  val id: Long? = null,
  val startDateTime: OffsetDateTime? = null,
  val endDateTime: OffsetDateTime? = null,
  val private: Boolean? = null,
  val question: String? = null,
  val votes: List<VoteRequest>? = null,
)

data class PollResponse(
  val id: Long,
  val startDateTime: OffsetDateTime?,
  val endDateTime: OffsetDateTime?,
  val private: Boolean,
  val question: String,
  val votes: List<VoteResponse>,
)

fun PollRequest.toPoll(): Poll {
  val poll = Poll()
  poll.id = id ?: -1
  poll.startDate = startDateTime
  poll.endDate = endDateTime
  poll.private = private ?: false
  poll.question = question ?: ""
  poll.votes = votes?.mapTo(HashSet()) { it.toVote() } ?: HashSet()
  return poll
}

fun Poll.toResponse(): PollResponse {
  return PollResponse(id, startDate, endDate, private, question, votes.map { it.toResponse() })
}
