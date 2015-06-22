package poly.algebra.std

import poly.algebra.hkt._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object SeqStructure extends ConcatenativeMonad[Seq] {

  def flatMap[x, y](sx: Seq[x])(f: x => Seq[y]) = sx.flatMap(f)

  def concat[x](sx: Seq[x], sy: Seq[x]) = sx ++ sy

  def id[x](u: x): Seq[x] = Seq(u)

  def empty[x]: Seq[x] = Seq()

}
