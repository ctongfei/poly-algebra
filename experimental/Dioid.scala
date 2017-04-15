package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a dioid, also known as a canonically ordered semiring.
 * @since 0.4.x
 * @author Tongfei Chen
 */
trait Dioid[@sp(fdi) X] extends Semiring[X] {

  def closure(x: X): X

}

object Dioid extends ImplicitGetter[Dioid]
