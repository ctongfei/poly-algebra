package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.5
 */
trait Bijection[X, Y] extends (X => Y) { self =>

  import Bijection._

  def invert(y: Y): X

  implicit def inverse: Bijection[Y, X] = new Bijection[Y, X] {
    def invert(x: X): Y = self.apply(x)
    def apply(y: Y): X = self.invert(y)
  }

  def andThen[Z](that: Bijection[Y, Z]): Bijection[X, Z] = new Bijection[X, Z] {
    def invert(z: Z): X = self.invert(that.invert(z))
    def apply(x: X): Z = that(self(x))
  }

  def compose[W](that: Bijection[W, X]): Bijection[W, Y] = that andThen self

}

object Bijection extends BinaryImplicitGetter[Bijection] {

  /** ASCII-compliant symbolic alias for bijections. */
  type <=>[X, Y] = Bijection[X, Y]
  /** Symbolic alias for bijections. */
  type ⇔[X, Y] = Bijection[X, Y]

  def create[X, Y](f1: X => Y, f2: Y => X): Bijection[X, Y] = new (X <=> Y) {
    def invert(y: Y) = f2(y)
    def apply(x: X): Y = f1(x)
  }

  implicit def IdentityBijection[X]: Bijection[X, X] = new (X <=> X) {
    def invert(x: X): X = x
    def apply(x: X): X = x
  }

  implicit def SwapBijection[X, Y]: Bijection[(X, Y), (Y, X)] = new ((X, Y) <=> (Y, X)) {
    def invert(y: (Y, X)): (X, Y) = y.swap
    def apply(x: (X, Y)): (Y, X) = x.swap
  }

}
