package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents the existence of a maximum `top` element.
 *
 * An instance of this typeclass should satisfy the following axiom:
 *  - $lawTop
 * @define lawTop '''Top element''': ∀''a''∈X, ''a'' <= top.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.5
 */
trait HasTop[@sp(fdib) +X] {
  /** The maximum `top` element of this type. */
  def top: X

  def asIdentityWithTop: HasIdentity[X] = new HasIdentity[X] {
    def id: X = top
  }

}

object HasTop extends ImplicitGetter[HasTop] {

  /** Creates a `HasTop` object given an maximum element `top` of a type. */
  def create[@sp(fdi) X](topElem: X) = new HasTop[X] {
    def top = topElem
  }
}
