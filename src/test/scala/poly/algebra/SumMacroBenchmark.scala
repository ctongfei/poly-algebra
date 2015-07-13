package poly.algebra

import poly.algebra.implicits._
import poly.algebra.functions._
import org.scalameter._
import scala.language.postfixOps

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object SumMacroBenchmark extends App {

  for (n ← Seq(200000, 400000, 800000, 1600000)) {

    val conf = config(Key.exec.benchRuns → 30)
    .withWarmer(new Warmer.Default).withMeasurer(new Measurer.IgnoringGC)

    val t1 = conf measure {
      var sum = 0.0
      var i = 0
      while (i < n) {
        sum += i * 0.1
        i += 1
      }
      sum
    }
    println(s"While loop: $t1")


    val t2 = conf measure {∑(n)(i => i * 0.1)}
    println(s"BigOps sum macro: $t2")

    val t3 = conf measure {
      var sum = 0.0
      for (i ← 0 until n)
        sum += i * 0.1
      sum
    }
    println(s"Imperative Scala: $t3")

    val t4 = conf measure {
      (0 until n).view.map(i => i * 0.1).sum
    }
    println(s"Idiomatic Scala with view: $t4")

    val t5 = conf measure {
      (0 until n).map(i => i * 0.1).sum
    }
    println(s"Idiomatic Scala: $t5")

    println()
  }

}
