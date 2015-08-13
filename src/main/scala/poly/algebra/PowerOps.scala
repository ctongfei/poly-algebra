package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

trait PowerOps[@sp(fd) X] {
  def root(x: X, n: Int): X
  def sqrt(x: X): X = root(x, 2)
  def cbrt(x: X): X = root(x, 3)
  def pow(x: X, y: X): X
}

object PowerOps extends ImplicitGetter[PowerOps]
