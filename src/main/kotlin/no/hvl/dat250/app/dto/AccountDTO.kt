package no.hvl.dat250.app.dto

import no.hvl.dat250.app.model.Account
import java.util.*

/**
 * @author Elg
 */
data class AccountRequest(
  val uuid: String? = null,
  val admin: Boolean? = null,
  val polls: List<PollRequest>? = null,
  val name: String? = null,
  val email: String? = null,
  val password: String? = null,
  val votes: Map<PollRequest, VoteRequest>? = null
)

data class AccountResponse(
  val uuid: String,
  val admin: Boolean,
  val polls: List<PollResponse>,
  val name: String,
  val email: String,
  val votes: Map<PollResponse, VoteResponse>
)

fun AccountRequest.toAccount(): Account {
  val account = Account()
  account.uuid = uuid ?: UUID.randomUUID().toString()
  account.admin = admin ?: false
  account.polls = polls?.mapTo(HashSet()) { it.toPoll() } ?: HashSet()
  account.name = name ?: ""
  account.email = email ?: ""
  account.password = password
  account.votes = votes?.mapKeys { it.key.toPoll() }?.mapValuesTo(HashMap()) { it.value.toVote() } ?: HashMap()
  return account
}

fun Account.toResponse(): AccountResponse {
  return AccountResponse(
    uuid,
    admin,
    polls.map { it.toResponse() },
    name,
    email,
    votes.mapKeys { it.key.toResponse() }.mapValues { it.value.toResponse() }
  )
}
