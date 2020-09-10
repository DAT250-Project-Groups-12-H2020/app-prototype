package no.hvl.dat250.jpa.bank

import javax.persistence.Persistence

private const val PERSISTENCE_UNIT_NAME = "todos"

fun main(args: Array<String>) {
    val factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
    val em = factory.createEntityManager()
    // read the existing entries and write to console
    

    em.close()
}

