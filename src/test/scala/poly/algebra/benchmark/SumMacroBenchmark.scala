package poly.algebra.benchmark

import org.scalameter._
import poly.algebra.syntax._

import scala.language.postfixOps

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object SumMacroBenchmark extends App {

  for (n ← Seq(100000, 200000, 400000, 800000, 1600000, 3200000)) {

    val conf = config(Key.exec.benchRuns → 20)
    .withWarmer(new Warmer.Default).withMeasurer(new Measurer.IgnoringGC)

    var sum = 0.0
    val t1 = conf measure {
      sum = 0.0
      var i = 0
      while (i < n) {
        sum += i * 0.1
        i += 1
      }
      sum
      print(s"$sum\r")
    }
    println(s"While loop: $t1")
    println(sum)

    var sum2 = 0.0
    val t2 = conf measure {
      sum2 = Sum(n)(i => i * 0.1)
      print(s"$sum\r")
    }
    println(s"BigOps sum macro: $t2")
    println(sum2)

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
