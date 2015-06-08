package poly.algebra

import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait SignOps[@sp(di) X] {

  def abs(x: X): X
  def sgn(x: X): X

}
