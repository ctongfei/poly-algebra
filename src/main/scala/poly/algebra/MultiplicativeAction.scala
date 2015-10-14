package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait MultiplicativeAction[X, @sp(fdi) S] {

  def scale(x: X, k: S): X

}

object MultiplicativeAction extends BinaryImplicitGetter[MultiplicativeAction]
