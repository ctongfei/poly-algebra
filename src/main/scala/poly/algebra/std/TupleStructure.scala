package poly.algebra.std

import poly.algebra.hkt._

/**
 * @author Tongfei Chen
 */
object TupleStructure extends Bifunctor[Tuple2] {
  def map1[A, B, C](t: (A, B))(f1: A => C) = (f1(t._1), t._2)

  def map2[A, B, C](t: (A, B))(f2: B => C) = (t._1, f2(t._2))

  override def mapBoth[A, B, C, D](t: (A, B))(f1: A => C, f2: B => D) = (f1(t._1), f2(t._2))
}
