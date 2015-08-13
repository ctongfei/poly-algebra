package poly.algebra.std

import poly.algebra.hkt._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object SeqStructure extends ConcatenativeMonad[Seq] {

  def flatMap[X, Y](sx: Seq[X])(f: X => Seq[Y]) = sx.flatMap(f)

  def concat[X](sx: Seq[X], sy: Seq[X]) = sx ++ sy

  def id[X](u: X): Seq[X] = Seq(u)

  def empty[X]: Seq[X] = Seq()

}
