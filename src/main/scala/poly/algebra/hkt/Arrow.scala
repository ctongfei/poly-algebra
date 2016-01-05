package poly.algebra.hkt

import poly.algebra.factory._

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * Represents an arrow.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.9
 */
trait Arrow[⇾[_, _]] extends Category[⇾] with Profunctor[⇾] {

  def lift[X, Y](f: X => Y): X ⇾ Y

  def apply1[X, Y, Z](f: X ⇾ Y): (X, Z) ⇾ (Y, Z)

  def apply2[X, Y, Z](f: X ⇾ Y): (Z, X) ⇾ (Z, Y)

  def map[X, Y, Z](f: X ⇾ Y)(g: Y => Z) = andThen(f, lift(g))

  def contramap[X, Y, Z](g: Y ⇾ Z)(f: X => Y) = compose(g, lift(f))
}

object Arrow extends Unary2TImplicitHktGetter[Arrow]
