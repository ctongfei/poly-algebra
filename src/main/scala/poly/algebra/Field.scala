package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a field.
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait Field[@sp(fd) X] extends CRing[X] with MultiplicativeCGroup[X] {

  /** Returns the 1/2 element in this field. */
  def half = div(one, two)

}

object Field extends ImplicitGetter[Field]
