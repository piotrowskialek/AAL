import java.util.concurrent.TimeoutException

import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import util.control.Breaks._

/**
  * Created by apiotrowski on 08.06.2017.
  */

object Workspace {
  var liczbaKolorow: Int = 0
}

class Workspace(val liczbaPojemnikow: Int, val liczbaKolorow: Int) {

  //liczba kolorow = k
  //zmienna z maina
  Workspace.liczbaKolorow = liczbaKolorow


  //liczba pojemnikow = n
  //ograniczenie pojemnosci pojemnikow -- Pi ? nie wiem jakie, zeby dzialalo
  val ograniczeniePojemnosci = 20


  var kolekcjaPojemnikow: Array[Pojemnik] =
    Array.fill(liczbaPojemnikow)(new Pojemnik(randP(ograniczeniePojemnosci), this))


  def alg1(): Boolean = {
    //iteruj po wszystkich pojemnikach
    //dla każdego pojemnika sprawdzamy czy jest tam więcej niż jeden klocek tego samego koloru
    //jeżeli tak to przenosimy wszystkie nadmiarowe do kolejnego pojemnika
    //Jeżeli nie, przechodzimy do sprawdzania pojemnika obok


    var buffer: ArrayBuffer[Klocek] = new ArrayBuffer[Klocek]()
    var failCounter: Int = 0

    for (p: Pojemnik <- kolekcjaPojemnikow) {

      val tmp = buffer.toArray

      kolekcjaPojemnikow(p.id).kolekcjaKlockow = kolekcjaPojemnikow(p.id).kolekcjaKlockow ++ tmp

      buffer.clear()
      //sprawdz czy jest tam wiecej niz jeden klocek tego samego koloru


      val col = p.kolekcjaKlockow.sorted


      for (i <- 0 to liczbaKolorow - 1) {
        var tmp = col.count(k => k.kolor == i)
        if (tmp > 1) {

          p.kolekcjaKlockow = p.kolekcjaKlockow.filter(k => k.kolor != i) :+ new Klocek(i)

          //dodac tmp-1 elementow do bufora

          buffer.appendAll(List.fill(tmp - 1)(new Klocek(i)))
        }
      }

      if (p.id == liczbaPojemnikow - 1) {
        val bufferArray = buffer.toArray
        kolekcjaPojemnikow(0).kolekcjaKlockow = kolekcjaPojemnikow(0).kolekcjaKlockow ++ bufferArray
        if (bufferArray.length > 0) {
          failCounter += 1
          return false
        }
        else
          return true

      }
    }
    true
  }

  def alg2(): Unit = {
    //iteruj po wszystkich pojemnikach
    //dla każdego pojemnika sprawdzamy czy jest tam więcej niż jeden klocek tego samego koloru
    //jeżeli tak to sprawdzamy czy mozemy go przerzucic na lewo lub prawo dopoki nie oproznimy bufora
    //Jeżeli nie, przechodzimy do sprawdzania pojemnika obok


    var buffer: ArrayBuffer[Klocek] = new ArrayBuffer[Klocek]()


    for (p: Pojemnik <- kolekcjaPojemnikow) {

      val tmp = buffer.toArray

      //sprawdz po lewo i prawo

      for (j <- 1 to liczbaPojemnikow/2 + 1) {
        for (it <- buffer) {

          breakable {

            if (!kolekcjaPojemnikow(p.id + j).kolekcjaKlockow.contains(it)) {
              kolekcjaPojemnikow(p.id + j).kolekcjaKlockow :+= it
              buffer -= it
              break()
            } //sprawdz w lewo i prawo

            if (!kolekcjaPojemnikow(p.id - j).kolekcjaKlockow.contains(it)) {
              kolekcjaPojemnikow(p.id - j).kolekcjaKlockow :+= it
              buffer -= it
              break()
            }
          }
        }

      }
      kolekcjaPojemnikow(p.id).kolekcjaKlockow = kolekcjaPojemnikow(p.id).kolekcjaKlockow ++ tmp

      buffer.clear()
      //sprawdz czy jest tam wiecej niz jeden klocek tego samego koloru


      val col = p.kolekcjaKlockow.sorted


      for (i <- 0 to liczbaKolorow - 1) {
        var tmp = col.count(k => k.kolor == i)
        if (tmp > 1) {

          p.kolekcjaKlockow = p.kolekcjaKlockow.filter(k => k.kolor != i) :+ new Klocek(i)

          //dodac tmp-1 elementow do bufora

          buffer.appendAll(List.fill(tmp - 1)(new Klocek(i)))
        }
      }

      //jezeli ostatni, przerzuc do pierwszego
      if (p.id == liczbaPojemnikow - 1) {
        val bufferArray = buffer.toArray
        kolekcjaPojemnikow(0).kolekcjaKlockow = kolekcjaPojemnikow(0).kolekcjaKlockow ++ bufferArray

      }
    }
  }


  def checkQuantity(kolor: Int): Int = {

    if (kolekcjaPojemnikow != null) {
      kolekcjaPojemnikow
        .filter(p => p != null)
        .flatMap(p => p.kolekcjaKlockow)
        .count(k => k.kolor == kolor)
    } else
      return 0;
  }

  def printSum(): Int = {
    var result: Int = 0
    for (i <- 0 to liczbaKolorow) {
      result += checkQuantity(i)
    }
    result
  }


  def randP(modulo: Int): Int = {
    var result = new Random().nextInt(Int.MaxValue) % modulo
    while (result == 0)
      result = new Random().nextInt(Int.MaxValue) % modulo
    return result
  }

  def printWorkspace(): Unit = {
    println("Ilosc pojemnikow:" + kolekcjaPojemnikow.length)
    kolekcjaPojemnikow.foreach(p => p.printKlocki())
    println()
  }
}
