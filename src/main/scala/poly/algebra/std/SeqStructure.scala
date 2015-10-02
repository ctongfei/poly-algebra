package poly.algebra.std

import poly.algebra.hkt._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object SeqStructure extends ConcatenativeMonad[Seq] {

  def flatMap[X, Y](sx: Seq[X])(f: X => Seq[Y]) = sx flatMap f

  def concat[X](sx: Seq[X], sy: Seq[X]) = sx ++ sy

  def id[X](u: X): Seq[X] = Seq(u)

  def empty[X]: Seq[X] = Seq()

  override def map[X, Y](sx: Seq[X])(f: X => Y) = sx map f

  override def product[X, Y](sx: Seq[X])(sy: Seq[Y]) = sx.flatMap(x => sy.map(y => (x, y)))

}
