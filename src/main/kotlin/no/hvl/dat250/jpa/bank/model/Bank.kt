package no.hvl.dat250.jpa.bank.model

import javax.persistence.*

@Entity
class Bank {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    lateinit var name: String

    @get:OneToMany
    @get:JoinTable
    lateinit var creditCards: MutableList<CreditCard>

}
