package no.hvl.dat250.app.model

import javax.persistence.*

@Entity
class CreditCard {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    @get:OneToOne
    lateinit var pin: PinCode

    @get:ManyToOne
    @get:JoinTable
    lateinit var person: Person

    var number: Int = 0
    var limit: Int = 0
    var balance: Int = 0
}
