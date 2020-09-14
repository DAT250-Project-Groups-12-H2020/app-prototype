package no.hvl.dat250.app.model

import java.time.OffsetDateTime
import javax.persistence.*

@Entity
class Poll {

  @field:Id
  @field:GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = -1

  var startDate: OffsetDateTime? = null

  var endDate: OffsetDateTime? = null

  var private: Boolean = false

  lateinit var question: String

  @field:OneToMany
  lateinit var votes: MutableSet<Vote>

}
