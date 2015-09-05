package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents the existence of a minimum `bottom` element.
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

object HasBottom extends ImplicitGetter[HasBottom] {

  /** Creates a `HasBottom` object given an minimum element `bot` of a type. */
  def create[@sp(fdi) X](botElem: X) = new HasBottom[X] {
    def bot = botElem
  }
}
