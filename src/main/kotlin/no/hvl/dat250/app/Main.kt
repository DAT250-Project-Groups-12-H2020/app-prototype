package no.hvl.dat250.app

import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

const val PERSISTENCE_UNIT_NAME = "app"

val factory: EntityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)

fun main(args: Array<String>) {
  val entityManager = factory.createEntityManager()
  // read the existing entries and write to console


  entityManager.close()
}

