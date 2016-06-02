package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._
import poly.algebra.syntax._

/**
 * Represents an one-to-one (bijective) function between two types.
 * @author Tongfei Chen
 * @since 0.2.5
 */
trait Bijection[@sp(Int, AnyRef) X, @sp(Int, AnyRef) Y] extends (X => Y) { self =>

  /** Given a value ''y'' in the codomain, returns the unique value ''x'' in the domain that maps to ''y''. */
  def invert(y: Y): X

  /** Returns the inverse bijection of this bijection. */
  def inverse: Bijection[Y, X] = new BijectionT.Inverse(self)

  /** Composes two bijections with this bijection applied first. */
  def andThen[Z](that: Bijection[Y, Z]): Bijection[X, Z] = new BijectionT.Composed(that, self)

  /** Composes two bijections with this bijection applied last. */
  def compose[W](that: Bijection[W, X]): Bijection[W, Y] = new BijectionT.Composed(self, that)

  /** Returns the Cartesian product of two bijections. */
  def product[U, V](that: Bijection[U, V]): Bijection[(X, U), (Y, V)] = new BijectionT.Product(self, that)

}

object Bijection extends BinaryImplicitGetter[Bijection] {

  def create[X, Y](f1: X => Y, f2: Y => X): X <=> Y = new AbstractBijection[X, Y] {
    def invert(y: Y) = f2(y)
    def apply(x: X): Y = f1(x)
  }

  implicit object Category extends Category[Bijection] {
    def id[X] = Bijection.create(x => x, x => x)
    def compose[X, Y, Z](g: Y <=> Z, f: X <=> Y) = g compose f
  }

  def IdentityBijection[X]: X <=> X = new BijectionT.Identity[X]

}

abstract class AbstractBijection[@sp(Int, AnyRef) X, @sp(Int, AnyRef) Y] extends Bijection[X, Y]

private[poly] object BijectionT {

  class Identity[@sp(Int, AnyRef) X] extends Bijection[X, X] {
    def invert(x: X) = x
    def apply(x: X) = x
    override def inverse = this
  }

  class Inverse[@sp(Int, AnyRef) X, @sp(Int, AnyRef) Y](self: Bijection[X, Y]) extends Bijection[Y, X] {
    def invert(x: X) = self.apply(x)
    def apply(y: Y) = self.invert(y)
    override def inverse = self
  }

  class Composed[W, X, Y](self: Bijection[X, Y], that: Bijection[W, X]) extends AbstractBijection[W, Y] {
    def invert(y: Y) = that.invert(self.invert(y))
    def apply(w: W) = self(that(w))
  }

  class Product[X, Y, U, V](self: Bijection[X, Y], that: Bijection[U, V]) extends AbstractBijection[(X, U), (Y, V)] {
    def invert(yv: (Y, V)) = (self.invert(yv._1), that.invert(yv._2))
    def apply(xu: (X, U)) = (self(xu._1), that(xu._2))
  }

}
