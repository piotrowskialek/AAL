import java.util.concurrent.TimeoutException

/**
  * Created by apiotrowski on 08.06.2017.
  */


object Main extends App {

  //1. N PONUMEROWANYCH KOLEJNO POJEMNIKOW
  //2. POJEMNOŚĆ POJEMNIKA P -- całkowicie losowe, ale z ograniczeniami
  //3. WE WSZYSTKICH POJEMNIKACH SA KLOCKI W K KOLORACH -- kolor to int
  //4. ŁĄCZNIE WE WSZYSTKICH POJEMNIKACH JEST NIE WIĘCEJ NIŻ N KLOCKÓW W JEDNYM KOLORZE

  //1
  //ustalona liczba kolorow i wsp wypelnienia
  //zwiekszajaca sie liczba pojemnikow


  //2
  //ustalony rozmiar problemu, zwiekszamy k


  //3
  //zwiekszajac liczbe klockow zwiekszamy liczbe kolorow k
  //przeszacowanie mozliwe

  val n = 8
  val k = 10

  var iteracja = 0
  val workspace = new Workspace(n,k)

  //rozgrzewka
  for(i <- 1 to 5){
    try {
      benchmark()
    } catch {
      case e: Exception => println(e.getMessage)
    }
  }

  println("Ilosc klockow w workspace:"+workspace.printSum())
  workspace.printWorkspace()

  var before = System.nanoTime()
  benchmark()
  var after = System.nanoTime()

  println("Iteracja:" + iteracja)
  println("Ilosc klockow w workspace:"+workspace.printSum())
  workspace.printWorkspace()

  println("CZAS TRWANIA: " + (after-before) + " ns == " + (after-before)/1000000 + " ms")



  def benchmark(): Unit = {

    while(workspace.alg1()!=true){
      iteracja+=1
      if(iteracja>100)
        throw new TimeoutException("Too many tries!")
    }

  }

}
