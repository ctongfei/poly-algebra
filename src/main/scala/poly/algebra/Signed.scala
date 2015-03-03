package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Signed[@miniboxed X] {

  def abs(x: X): X
  def sgn(x: X): X

}
