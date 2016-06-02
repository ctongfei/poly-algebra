package poly.algebra.std

import poly.algebra._
import poly.algebra.hkt._

/**
 * @author Tongfei Chen
 * @since 0.3.8
 */
object IdStructure extends Monad[Id] {
  def flatMap[X, Y](x: Id[X])(f: X ⇒ Id[Y]) = f(x)
  def id[X](u: X) = u
  override def map[X, Y](x: Id[X])(f: X => Y): Id[Y] = f(x)
  override def flatten[Y](y: Id[Id[Y]]): Id[Y] = y
  override def liftedMap[X, Y](x: Id[X])(f: Id[X => Y]): Id[Y] = f(x)
  override def product[X, Y](x: Id[X])(y: Id[Y]): Id[(X, Y)] = (x, y)
}
