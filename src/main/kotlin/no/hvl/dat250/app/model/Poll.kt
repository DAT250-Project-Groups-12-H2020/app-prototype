package no.hvl.dat250.app.model

import java.time.OffsetDateTime
import javax.persistence.*

@Entity
class Poll {

  @field:Id
  @field:GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  var startDate: OffsetDateTime? = null

  var endDate: OffsetDateTime? = null

  var private: Boolean = false

  lateinit var question: String
  lateinit var firstAnswer: String
  lateinit var secondAnswer: String

  @field:OneToMany(cascade = [CascadeType.ALL])
  lateinit var votes: MutableSet<Vote>

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Poll) return false

    if (id != other.id) return false
    if (startDate != other.startDate) return false
    if (endDate != other.endDate) return false
    if (private != other.private) return false
    if (question != other.question) return false
    if (firstAnswer != other.firstAnswer) return false
    if (secondAnswer != other.secondAnswer) return false
    if (votes != other.votes) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + (startDate?.hashCode() ?: 0)
    result = 31 * result + (endDate?.hashCode() ?: 0)
    result = 31 * result + private.hashCode()
    result = 31 * result + question.hashCode()
    result = 31 * result + firstAnswer.hashCode()
    result = 31 * result + secondAnswer.hashCode()
    result = 31 * result + votes.hashCode()
    return result
  }

  override fun toString(): String {
    return "Poll(id=$id, startDate=$startDate, endDate=$endDate, private=$private, question='$question')"
  }


}
