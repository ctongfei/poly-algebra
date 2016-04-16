package poly.algebra.std

import poly.algebra._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen
 * @since 0.3.2
 */
class DefaultHashing[@sp X] extends IntHashing[X] {
  def hash(x: X) = x.##
  def eq(x: X, y: X) = x == y
}

