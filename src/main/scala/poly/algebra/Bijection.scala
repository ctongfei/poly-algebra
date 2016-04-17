package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._
import poly.algebra.syntax._

/**
 * Represents an one-to-one function between two types.
 * @author Tongfei Chen
 * @since 0.2.5
 */
trait Bijection[@sp(Int, AnyRef) X, @sp(Int, AnyRef) Y] extends (X => Y) { self =>

  /** Given a value ''y'' in the codomain, returns the unique value in the domain that maps to ''y''. */
  def invert(y: Y): X

  /** Returns the inverse bijection of this bijection. */
  def inverse: Bijection[Y, X] = new Bijection[Y, X] {
    def invert(x: X) = self.apply(x)
    def apply(y: Y) = self.invert(y)
    override def inverse = self
  }

  def andThen[Z](that: Bijection[Y, Z]) = that compose self

  def compose[W](that: Bijection[W, X]): Bijection[W, Y] = new Bijection[W, Y] {
    def invert(y: Y) = that.invert(self.invert(y))
    def apply(w: W) = self(that(w))
  }

  def product[U, V](that: Bijection[U, V]): Bijection[(X, U), (Y, V)] = new Bijection[(X, U), (Y, V)] {
    def invert(yv: (Y, V)) = (self.invert(yv._1), that.invert(yv._2))
    def apply(xu: (X, U)) = (self(xu._1), that(xu._2))
  }

}

object Bijection extends BinaryImplicitGetter[Bijection] {

  def create[X, Y](f1: X => Y, f2: Y => X): X <=> Y = new (X <=> Y) {
    def invert(y: Y) = f2(y)
    def apply(x: X): Y = f1(x)
  }

  implicit object Category extends Category[Bijection] {
    def id[X] = Bijection.create(x => x, x => x)
    def compose[X, Y, Z](g: Y <=> Z, f: X <=> Y) = g compose f
  }

  implicit def IdentityBijection[X]: X <=> X = new (X <=> X) {
    def invert(x: X): X = x
    def apply(x: X): X = x
  }

  implicit def SwapBijection[X, Y]: (X, Y) <=> (Y, X) = new ((X, Y) <=> (Y, X)) {
    def invert(y: (Y, X)): (X, Y) = y.swap
    def apply(x: (X, Y)): (Y, X) = x.swap
  }
}
