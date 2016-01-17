package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
  * Represents a total order in which each element's predecessor and successor can be accessed.
  * This is equivalent to the Haskell typeclass `Enum`.
  * @author Tongfei Chen
  * @since 0.2.19
  */
trait SequentialOrder[@sp(il) X] extends TotalOrder[X] {

  def pred(x: X): X
  def succ(x: X): X

  def predN(x: X, n: Int): X = {
    var i = 0
    var r = x
    while (i < n) {
      r = pred(r)
      i += 1
    }
    r
  }

  def succN(x: X, n: Int): X = {
    var i = 0
    var r = x
    while (i < n) {
      r = succ(r)
      i += 1
    }
    r
  }

}

object SequentialOrder extends ImplicitGetter[SequentialOrder]
