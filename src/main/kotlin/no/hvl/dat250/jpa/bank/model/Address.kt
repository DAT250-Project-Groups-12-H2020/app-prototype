package no.hvl.dat250.jpa.bank.model

import javax.persistence.*

@Entity
class Address {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    lateinit var street: String
    var number: Int = 0

    @get:ManyToMany(mappedBy = "address")
    lateinit var persons: MutableList<Person>

}
