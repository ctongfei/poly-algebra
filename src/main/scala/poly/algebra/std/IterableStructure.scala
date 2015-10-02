package poly.algebra.std

import poly.algebra.hkt._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object IterableStructure extends ConcatenativeMonad[Iterable] {
  def id[X](u: X) = Iterable(u)
  def flatMap[X, Y](mx: Iterable[X])(f: X => Iterable[Y]) = mx flatMap f
  override def map[X, Y](mx: Iterable[X])(f: X => Y) = mx map f
  override def filter[X](mx: Iterable[X])(f: X => Boolean) = mx filter f
  def empty[X] = Iterable()
  def concat[X](sx: Iterable[X], sy: Iterable[X]) = sx ++ sy
}
