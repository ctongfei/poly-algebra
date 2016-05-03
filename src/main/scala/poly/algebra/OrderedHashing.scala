package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._

/**
 * Represents a weak order endowed with a hashing function.
 *
 * @author Tongfei Chen
 * @since 0.3.3
 */
trait OrderedHashing[@sp -X] extends Order[X] with Hashing[X] { self =>

  override def contramap[@sp Y](f: Y => X): OrderedHashing[Y] = new OrderedHashing[Y] {
    def cmp(x: Y, y: Y) = self.cmp(f(x), f(y))
    def hash(x: Y) = self.hash(f(x))
  }

}

object OrderedHashing extends ImplicitGetter[OrderedHashing] {

  implicit object ContravariantFunctor extends ContravariantFunctor[OrderedHashing] {
    def contramap[X, Y](mx: OrderedHashing[X])(f: Y => X) = mx contramap f
  }

}
