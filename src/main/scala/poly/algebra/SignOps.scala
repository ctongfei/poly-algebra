package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait SignOps[@specialized(Int, Double) X] {

  def abs(x: X): X
  def sgn(x: X): X

}
