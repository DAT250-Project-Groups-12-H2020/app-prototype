package no.hvl.dat250.app.model

import org.eclipse.persistence.annotations.UuidGenerator
import javax.persistence.*

@Entity
class Account {

  @get:UuidGenerator(name = "uuid")
  @get:Id
  @get:GeneratedValue(generator = "uuid")
  lateinit var uuid: String

  var admin: Boolean = false

  @get:OneToMany
  @get:JoinColumn
  lateinit var polls: MutableSet<Poll>

  lateinit var name: String
  lateinit var email: String
  var password: String? = null
}
