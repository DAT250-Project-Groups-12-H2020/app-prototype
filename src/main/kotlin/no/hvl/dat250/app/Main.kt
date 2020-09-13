package no.hvl.dat250.app

import javax.persistence.Persistence

const val PERSISTENCE_UNIT_NAME = "app"


fun main(args: Array<String>) {
  val factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
  val entityManager = factory.createEntityManager()
  // read the existing entries and write to console


  entityManager.close()
}

