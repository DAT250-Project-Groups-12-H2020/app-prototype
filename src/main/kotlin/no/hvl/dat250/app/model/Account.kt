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
}
