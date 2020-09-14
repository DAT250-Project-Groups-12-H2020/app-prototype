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
  val password: String? = null
)

data class AccountResponse(
  val uuid: String,
  val admin: Boolean,
  val polls: List<PollResponse>,
  val name: String,
  val email: String
)

fun AccountRequest.toAccount(): Account {
  val account = Account()
  account.uuid = uuid ?: UUID.randomUUID().toString()
  account.admin = admin ?: false
  account.polls = polls?.mapTo(HashSet()) { it.toPoll() } ?: HashSet()
  account.name = name ?: ""
  account.email = email ?: ""
  account.password = password
  return account
}

fun Account.toResponse(): AccountResponse {
  return AccountResponse(
    uuid,
    admin,
    polls.map { it.toResponse() },
    name,
    email
  )
}
