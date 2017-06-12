import scala.util.Random

/**
  * Created by apiotrowski on 08.06.2017.
  */

object Workspace{
  var liczbaKolorow:Int=0
}

class Workspace(val liczbaPojemnikow: Int, val liczbaKolorow: Int) {

  //liczba kolorow = k
  //zmienna z maina
  Workspace.liczbaKolorow=liczbaKolorow



  //liczba pojemnikow = n
  //ograniczenie pojemnosci pojemnikow -- Pi ? nie wiem jakie, zeby dzialalo
  val ograniczeniePojemnosci = 100



  var kolekcjaPojemnikow:Array[Pojemnik] =
    Array.fill(liczbaPojemnikow)(new Pojemnik(randP(ograniczeniePojemnosci)))


  def randP(modulo: Int): Int= {
    new Random().nextInt(Int.MaxValue)%modulo
  }

  def printWorkspace(): Unit = {
    println("Ilosc pojemnikow:"+kolekcjaPojemnikow.length)
    kolekcjaPojemnikow.foreach(p => p.printKlocki())
    println()
  }
}
