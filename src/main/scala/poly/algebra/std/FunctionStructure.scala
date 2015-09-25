package poly.algebra.std

import poly.algebra.hkt._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object FunctionStructure extends Category[Function] with Profunctor[Function] with Arrow[Function] {

  def id[X]: X => X = x => x
  def map[X, Y, Z](f: X => Y)(g: Y => Z): X => Z = g compose f
  def contramap[X, Y, Z](g: Y => Z)(f: X => Y) = g compose f

  def lift[X, Y](f: X => Y) = f
  def apply1[X, Y, Z](f: X => Y) = (x: X, z: Z) => (f(x), z)
  def apply2[X, Y, Z](f: X => Y) = (z: Z, x: X) => (z, f(x))
  def compose[X, Y, Z](f: Y => Z, g: X => Y) = f compose g

}
