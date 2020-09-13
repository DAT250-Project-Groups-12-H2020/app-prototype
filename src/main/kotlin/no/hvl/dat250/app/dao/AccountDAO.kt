package no.hvl.dat250.app.dao

import no.hvl.dat250.app.model.Account
import no.hvl.dat250.app.model.Poll

/**
 * @author Elg
 */
data class AccountRequest(
    val admin: Boolean,
    val polls: List<Poll>,
    val name: String,
    val email: String,
    val password: String
)

data class AccountResponse(
    val uuid: String,
    val admin: Boolean,
    val polls: List<Poll>,
    val name: String,
    val email: String
)

fun AccountRequest.toAccount(): Account {
    val account = Account()
    account.admin = admin
    account.polls = polls.toMutableSet()
    account.name = name
    account.email = email
    account.password = password
    return account
}

fun Account.toResponse(): AccountResponse {
    return AccountResponse(uuid, admin, polls.toList(), name, email)
}
