package no.hvl.dat250.app.model

import java.time.OffsetDateTime
import javax.persistence.*

/**
 * A vote can contain multiple sub-votes! As seen in the customer's design documentation.
 */
@Entity
class Vote {

  @field:Id
  @field:GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = -1

  var yesVotes: Int = 0
  var noVotes: Int = 0

  /**
   * When the vote was cast
   */
  lateinit var castTime: OffsetDateTime

  @field:ManyToOne
  var account: Account? = null
}
