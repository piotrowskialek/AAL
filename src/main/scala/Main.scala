/**
  * Created by apiotrowski on 08.06.2017.
  */

object Main extends App {

  //1. N PONUMEROWANYCH KOLEJNO POJEMNIKOW
  //2. POJEMNOŚĆ POJEMNIKA P -- całkowicie losowe, ale z ograniczeniami
  //3. WE WSZYSTKICH POJEMNIKACH SA KLOCKI W K KOLORACH -- kolor to int
  //4. ŁĄCZNIE WE WSZYSTKICH POJEMNIKACH JEST NIE WIĘCEJ NIŻ N KLOCKÓW W JEDNYM KOLORZE

  val n = 4
  val k = 2

  val workspace = new Workspace(n,k)

  workspace.printWorkspace()

  workspace.alg1()

  workspace.printWorkspace()

//  println(workspace.checkQuantity(5))
}
