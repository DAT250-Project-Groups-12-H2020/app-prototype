package no.hvl.dat250.jpa.bank.model

import javax.persistence.*

@Entity
class Person {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    @get:Id
    lateinit var name: String


    @get:OneToMany(mappedBy = "person")
    lateinit var creditCards: MutableList<CreditCard>

    @get:ManyToMany
    @get:JoinTable
    lateinit var address: MutableList<Address>

}
