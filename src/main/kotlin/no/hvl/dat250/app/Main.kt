package no.hvl.dat250.app

import javax.persistence.Persistence

private const val PERSISTENCE_UNIT_NAME = "app"

fun main(args: Array<String>) {
    val factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
    val em = factory.createEntityManager()
    // read the existing entries and write to console

  
    em.close()
}

