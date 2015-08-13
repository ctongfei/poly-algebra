package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait SignOps[@sp(fdi) X] {

  def abs(x: X): X
  def sgn(x: X): X

}

object SignOps extends ImplicitGetter[SignOps]
