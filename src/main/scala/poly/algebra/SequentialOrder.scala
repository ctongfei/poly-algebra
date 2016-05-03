package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._

/**
  * Represents a total order in which each element's predecessor and successor can be accessed.
  * This is equivalent to the Haskell typeclass `Enum`.
  * @author Tongfei Chen
  * @since 0.2.19
  */
trait SequentialOrder[@sp(Int) X] extends Order[X] { self =>

  /** Returns the preceding element of the given element under this sequential order. */
  def pred(x: X): X

  /** Returns the succeeding element of the given element under this sequential order. */
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

  override def reverse: SequentialOrder[X] = new SequentialOrder[X] {
    def pred(x: X) = self.succ(x)
    def succ(x: X) = self.pred(x)
    def cmp(x: X, y: X) = -self.cmp(x, y)
    override def reverse = self
  }

}

object SequentialOrder extends ImplicitGetter[SequentialOrder] {

}