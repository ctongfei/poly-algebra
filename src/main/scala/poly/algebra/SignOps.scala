package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen
 */
trait SignOps[@sp(fdi) X] {

  def sgn(x: X): X

}

object SignOps extends ImplicitGetter[SignOps]
