package poly.algebra.std

import poly.algebra.hkt._

/**
 * @author Tongfei Chen
 */
object OptionStructure extends Monad[Option] {

  def flatMap[X, Y](ox: Option[X])(f: X => Option[Y]) = ox flatMap f
  def id[X](u: X) = Some(u)
  override def map[X, Y](ox: Option[X])(f: X => Y) = ox map f

}
