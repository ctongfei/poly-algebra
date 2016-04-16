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

  def invert(y: Y): X

  /** Returns the inverse bijection of this bijection. */
  def inverse: Bijection[Y, X] = new Bijection.Inverse(self)

  def andThen[Z](that: Bijection[Y, Z]) = new Bijection.Composed(that, self)

  def compose[W](that: Bijection[W, X]) = new Bijection.Composed(self, that)

  def product[U, V](that: Bijection[U, V]) = new Bijection.Product(self, that)
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

  class Inverse[X, Y](override val inverse: Bijection[X, Y]) extends Bijection[Y, X] {
    def invert(x: X): Y = inverse.apply(x)
    def apply(y: Y): X = inverse.invert(y)
  }

  class Composed[X, Y, Z](outer: Bijection[Y, Z], inner: Bijection[X, Y]) extends Bijection[X, Z] {
    def invert(z: Z) = inner.invert(outer.invert(z))
    def apply(x: X) = outer(inner(x))
  }

  class Product[X, Y, U, V](first: Bijection[X, Y], second: Bijection[U, V]) extends Bijection[(X, U), (Y, V)] {
    def invert(yv: (Y, V)) = (first.invert(yv._1), second.invert(yv._2))
    def apply(xu: (X, U)) = (first(xu._1), second(xu._2))
  }
}
