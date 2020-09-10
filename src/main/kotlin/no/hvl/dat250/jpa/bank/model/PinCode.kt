package no.hvl.dat250.jpa.bank.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class PinCode {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    lateinit var pincode: String
    var count: Int = 0
}
