package poly.algebra.std

import poly.algebra.hkt._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object FunctionStructure extends Profunctor[Function1] with Arrow[Function1] {

  def id[X]: X => X = x => x
  override def map[X, Y, Z](f: X => Y)(g: Y => Z): X => Z = g compose f
  override def contramap[X, Y, Z](g: Y => Z)(f: X => Y) = g compose f

  def lift[X, Y](f: X => Y) = f
  def apply1[X, Y, Z](f: X => Y) = xz => (f(xz._1), xz._2)
  def apply2[X, Y, Z](f: X => Y) = zx => (zx._1, f(zx._2))
  def compose[X, Y, Z](f: Y => Z, g: X => Y) = f compose g

}
