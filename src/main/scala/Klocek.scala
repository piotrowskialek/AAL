/**
  * Created by apiotrowski on 08.06.2017.
  */

class Klocek(val kolor:Int) extends Ordered[Klocek]{

  override def toString: String = {
    "[" + kolor + "]"
  }

  def compare(that: Klocek) = kolor.compare(that.kolor)

}


