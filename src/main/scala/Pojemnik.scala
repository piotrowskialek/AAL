import scala.util.Random

/**
  * Created by apiotrowski on 08.06.2017.
  */

object Pojemnik {
  val liczbaKolorow: Int = Workspace.liczbaKolorow //k kolorow
  private var id:Int = 1
  private def increment(): Unit = {
    id+=1
  }
}


class Pojemnik(val pojemnosc:Int){

  val id: Int = Pojemnik.id
  Pojemnik.increment()

  var kolekcjaKlockow: Array[Klocek] = Array.fill(pojemnosc)(new Klocek(randK(Pojemnik.liczbaKolorow)))


  def randK(modulo: Int): Int= {
    new Random().nextInt(Int.MaxValue)%modulo
  }

  def printKlocki(): Unit = {
    println("Id pojemnika:" + id)
    println("Ilosc klockow:"+pojemnosc)
    kolekcjaKlockow.foreach(println)
    println()
  }
}
