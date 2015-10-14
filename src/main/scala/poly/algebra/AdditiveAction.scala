package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait AdditiveAction[X, @sp(fdi) S] {

  def translate(x: X, k: S): X

}

object AdditiveAction extends BinaryImplicitGetter[AdditiveAction]
