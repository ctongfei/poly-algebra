package poly.algebra.std

import poly.algebra.hkt._

/**
 * @author Tongfei Chen
 */
object EitherStructure extends Bifunctor[Either] {
  def mapFirst[A, B, C](e: Either[A, B])(f1: A => C) = e match {
    case Left(l) => Left(f1(l))
    case Right(r) => Right(r)
  }
  def mapSecond[A, B, C](e: Either[A, B])(f2: B => C) = e match {
    case Left(l) => Left(l)
    case Right(r) => Right(f2(r))
  }

  override def mapBoth[A, B, C, D](e: Either[A, B])(f1: A => C, f2: B => D) = e match {
    case Left(l) => Left(f1(l))
    case Right(r) => Right(f2(r))
  }
}
