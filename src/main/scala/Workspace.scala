import scala.collection.mutable.ArrayBuffer
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
  val ograniczeniePojemnosci = 3


  var kolekcjaPojemnikow:Array[Pojemnik] =
  Array.fill(liczbaPojemnikow)(new Pojemnik(randP(ograniczeniePojemnosci),this))


  def alg1(): Unit = {
    //iteruj po wszystkich pojemnikach
    //dla każdego pojemnika sprawdzamy czy jest tam więcej niż jeden klocek tego samego koloru
    //jeżeli tak to przenosimy wszystkie nadmiarowe do kolejnego pojemnika
    //Jeżeli nie, przechodzimy do sprawdzania pojemnika obok


    var buffer: ArrayBuffer[Klocek] = new ArrayBuffer[Klocek]()


    for(p: Pojemnik <- kolekcjaPojemnikow){

      val tmp = buffer.toArray

      kolekcjaPojemnikow(p.id).kolekcjaKlockow = kolekcjaPojemnikow(p.id).kolekcjaKlockow ++ tmp

      buffer.clear()
      //sprawdz czy jest tam wiecej niz jeden klocek tego samego koloru


      val col = p.kolekcjaKlockow.sorted


      for(i <-0 to liczbaKolorow-1){
        var tmp = col.count(k=>k.kolor==i)
        if(tmp>1){

          p.kolekcjaKlockow = p.kolekcjaKlockow.filter(k=>k.kolor!=i) :+ new Klocek(i)

          //dodac tmp-1 elementow do bufora

          buffer.appendAll(List.fill(tmp-1)(new Klocek(i)))
        }
      }

    }

  }

  def alg2(): Unit = {
    //iteruj po wszystkich pojemnikach
    //dla każdego pojemnika sprawdzamy czy jest tam więcej niż jeden klocek tego samego koloru
    //jeżeli tak to przenosimy wszystkie nadmiarowe do kolejnego pojemnika
    //Jeżeli nie, przechodzimy do sprawdzania pojemnika obok


    var buffer: ArrayBuffer[Klocek] = new ArrayBuffer[Klocek]()


    for(p: Pojemnik <- kolekcjaPojemnikow){

      val tmp = buffer.toArray

      kolekcjaPojemnikow(p.id).kolekcjaKlockow = kolekcjaPojemnikow(p.id).kolekcjaKlockow ++ tmp

      buffer.clear()
      //sprawdz czy jest tam wiecej niz jeden klocek tego samego koloru


      val col = p.kolekcjaKlockow.sorted


      for(i <-0 to liczbaKolorow-1){
        var tmp = col.count(k=>k.kolor==i)
        if(tmp>1){

          p.kolekcjaKlockow = p.kolekcjaKlockow.filter(k=>k.kolor!=i) :+ new Klocek(i)

          //dodac tmp-1 elementow do bufora

          buffer.appendAll(List.fill(tmp-1)(new Klocek(i)))
        }
      }

    }

  }


  def checkQuantity(kolor: Int): Int = {

    if(kolekcjaPojemnikow!=null) {
      kolekcjaPojemnikow
        .filter(p => p != null)
        .flatMap(p => p.kolekcjaKlockow)
        .count(k => k.kolor == kolor)
    } else
    return 0;
  }


  def randP(modulo: Int): Int= {
    var result = new Random().nextInt(Int.MaxValue) % modulo
    while(result==0)
      result = new Random().nextInt(Int.MaxValue) % modulo
    return result
  }

  def printWorkspace(): Unit = {
    println("Ilosc pojemnikow:"+kolekcjaPojemnikow.length)
    kolekcjaPojemnikow.foreach(p => p.printKlocki())
    println()
  }
}
