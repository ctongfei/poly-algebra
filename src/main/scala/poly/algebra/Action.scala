package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait Action[X, @sp(fdi) S] {

  def scale(x: X, k: S): X

}

object Action extends BinaryImplicitGetter[Action]
