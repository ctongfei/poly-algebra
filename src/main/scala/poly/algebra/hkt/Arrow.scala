package poly.algebra.hkt

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * Represents an arrow.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.3.0
 */
trait Arrow[⇾[-_, +_]] {

  def lift[X, Y](f: X => Y): X ⇾ Y

  def compose[X, Y, Z](f: Y ⇾ Z, g: X ⇾ Y): X ⇾ Z

  def apply1[X, Y, Z](f: X ⇾ Y): (X, Z) ⇾ (Y, Z)

  def apply2[X, Y, Z](f: X ⇾ Y): (Z, X) ⇾ (Z, Y)

}
