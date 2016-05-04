package poly.algebra.macroimpl

import scala.reflect.macros._
import scala.language.higherKinds

/**
 * Enables zero-overhead summoning of implicit typeclass instances (as
 * compared to the standard [[implicitly]], which has a method call overhead).
 * @since 0.3.5
 * @author Tongfei Chen
 */
object ImplicitSummoning {
  def summon[Ev[_], A](c: Context)(ev: c.Expr[Ev[A]]): c.Expr[Ev[A]] = ev
  def summonBinary[Ev[_, _], A, B](c: Context)(ev: c.Expr[Ev[A, B]]): c.Expr[Ev[A, B]] = ev
  def summonTernary[Ev[_, _, _], A, B, C](c: Context)(ev: c.Expr[Ev[A, B, C]]): c.Expr[Ev[A, B, C]] = ev
  def summonHkt[Ev[_[_]], A[_]](c: Context)(ev: c.Expr[Ev[A]]): c.Expr[Ev[A]] = ev
  def summonUnary2T[Ev[_[_, _]], A[_, _]](c: Context)(ev: c.Expr[Ev[A]]): c.Expr[Ev[A]] = ev
  def summonBinary1T[Ev[_[_], _[_]], A[_], B[_]](c: Context)(ev: c.Expr[Ev[A, B]]): c.Expr[Ev[A, B]] = ev
}
