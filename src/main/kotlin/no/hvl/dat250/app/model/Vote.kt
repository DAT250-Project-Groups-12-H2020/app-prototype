package no.hvl.dat250.app.model

import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * A vote can contain multiple sub-votes! As seen in the customer's design documentation.
 */
@Entity
class Vote {

  @field:Id
  @field:GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  var yesVotes: Int = 0
  var noVotes: Int = 0

  /**
   * When the vote was cast
   */
  lateinit var castTime: OffsetDateTime


  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Vote) return false

    if (id != other.id) return false
    if (yesVotes != other.yesVotes) return false
    if (noVotes != other.noVotes) return false
    if (castTime != other.castTime) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + yesVotes
    result = 31 * result + noVotes
    result = 31 * result + castTime.hashCode()
    return result
  }

  override fun toString(): String {
    return "Vote(id=$id, yesVotes=$yesVotes, noVotes=$noVotes, castTime=$castTime)"
  }


}
