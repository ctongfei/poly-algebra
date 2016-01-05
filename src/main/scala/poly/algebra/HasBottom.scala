package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents the existence of a minimum `bottom` element.
 *
 * An instance of this typeclass should satisfy the following axiom:
 *  - $lawBottom
 * @define lawBottom '''Bottom element''': ∀''a''∈X, ''a'' >= bot.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.5
 */
trait HasBottom[@sp(fdib) +X] {
  /** The minimum `bot` element of this type. */
  def bot: X

  def asIdentityWithBot: HasIdentity[X] = new HasIdentity[X] {
    def id: X = bot
  }

}

object HasBottom extends ImplicitGetter[HasBottom]
