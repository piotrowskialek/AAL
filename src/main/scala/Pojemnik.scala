import scala.util.Random

/**
  * Created by apiotrowski on 08.06.2017.
  */

object Pojemnik {
  val liczbaKolorow: Int = Workspace.liczbaKolorow //k kolorow
  private var id: Int = 0 //bylo 1
  private def increment(): Unit = {
    id+=1
  }
}


class Pojemnik(val pojemnosc:Int, val workspace: Workspace){

  val id: Int = Pojemnik.id
  Pojemnik.increment()



  var kolekcjaKlockow: Array[Klocek] = Array.fill(pojemnosc)(new Klocek(choseColor()))

  //trzeba zrobic zeby w workspace nie bylo wiecej niz n klockow o jednym kolorze


  def choseColor(): Int = { //moge tak zrobic bo bo budowania workspace moze trwac nieskonczonosc
    var r = randK(Pojemnik.liczbaKolorow)
    while(workspace.checkQuantity(r)==Pojemnik.liczbaKolorow)
      r = randK(Pojemnik.liczbaKolorow)
    return r
  }

  def randK(modulo: Int): Int = {
    new Random().nextInt(Int.MaxValue)%modulo
  }

  def printKlocki(): Unit = {
    println("Id pojemnika:" + id)
    println("Ilosc klockow:"+pojemnosc)
    kolekcjaKlockow.foreach(println)
    println()
  }
}
