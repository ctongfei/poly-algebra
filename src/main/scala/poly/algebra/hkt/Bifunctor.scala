package poly.algebra.hkt

import poly.algebra.factory._

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * @author Tongfei Chen
 */
trait Bifunctor[×[_, _]] {

  def map1[A, B, C](x: (A × B))(f: A => C): (C × B)

  def map2[A, B, D](x: (A × B))(f: B => D): (A × D)

  def mapBoth[A, B, C, D](x: (A × B))(f1: A => C, f2: B => D): (C × D) = map2(map1(x)(f1))(f2)

  def functorFirst[B]: Functor[({type λ[α] = α × B})#λ] = new Functor[({type λ[α] = α × B})#λ] {
    def map[A, C](x: (A × B))(f: A => C) = map1(x)(f)
  }

  def functorSecond[A]: Functor[({type λ[β] = A × β})#λ] = new Functor[({type λ[β] = A × β})#λ] {
    def map[B, C](x: (A × B))(f: B => C) = map2(x)(f)
  }

}

object Bifunctor extends Unary2TImplicitHktGetter[Bifunctor]
