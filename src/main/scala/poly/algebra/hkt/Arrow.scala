package poly.algebra.hkt

import poly.algebra.factory._

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * Represents an arrow.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.9
 */
trait Arrow[⇾[_, _]] extends Category[⇾] {

  def lift[X, Y](f: X => Y): X ⇾ Y

  def apply1[X, Y, Z](f: X ⇾ Y): (X, Z) ⇾ (Y, Z)

  def apply2[X, Y, Z](f: X ⇾ Y): (Z, X) ⇾ (Z, Y)

}

object Arrow extends BinaryImplicitHktGetter[Arrow]
