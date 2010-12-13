package code {
package snippet {

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import code.lib._
import Helpers._

import com.twitter.ostrich.{Stats}

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  def howdy(in: NodeSeq): NodeSeq = {
    Stats.incr("howdy-renders")
    Helpers.bind("b", in, "time" -> date.map(d => Text(d.toString)))
  }

  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy(in: NodeSeq): NodeSeq = Helpers.bind("b", in, "time" -> date.toString)
   */
   
  def importantNumber(in: NodeSeq): NodeSeq = {
    val n = Stats.time("important-number-calculation") {
      import scala.math._
      Thread.sleep(round(2000*random + 1000))
      7
    }
    <span>{n}</span>
  }
}

}
}
