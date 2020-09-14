package no.hvl.dat250.app.model

import org.eclipse.persistence.annotations.UuidGenerator
import javax.persistence.*

@Entity
class Account {

  @field:UuidGenerator(name = "uuid")
  @field:Id
  @field:GeneratedValue(generator = "uuid")
  lateinit var uuid: String

  var admin: Boolean = false

  @field:OneToMany
  @field:JoinColumn
  lateinit var polls: MutableSet<Poll>

  lateinit var name: String
  lateinit var email: String
  var password: String? = null
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Account) return false

    if (uuid != other.uuid) return false
    if (admin != other.admin) return false
    if (polls != other.polls) return false
    if (name != other.name) return false
    if (email != other.email) return false
    if (password != other.password) return false

    return true
  }

  override fun hashCode(): Int {
    var result = uuid.hashCode()
    result = 31 * result + admin.hashCode()
    result = 31 * result + polls.hashCode()
    result = 31 * result + name.hashCode()
    result = 31 * result + email.hashCode()
    result = 31 * result + (password?.hashCode() ?: 0)
    return result
  }

  override fun toString(): String {
    return "Account(uuid='$uuid', admin=$admin, polls=$polls, name='$name', email='$email', password=$password)"
  }


}
