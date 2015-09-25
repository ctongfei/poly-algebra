package poly.algebra.hkt

import scala.language.higherKinds

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.4
 */
trait Comonad[M[_]] extends Functor[M] {

  def extract[X](u: M[X]): X

  def extend[X, Y](mx: M[X])(f: M[X] => Y): M[Y]

  def map[X, Y](mx: M[X])(f: X => Y): M[Y] = extend(mx)(u => f(extract(u)))

  def duplicate[X](mx: M[X]): M[M[X]] = extend(mx)(u => u)

}
