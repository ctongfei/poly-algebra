package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */

trait InplaceAdditiveSemigroup[@specialized(Int, Double) X] extends AdditiveSemigroup[X] {
  def inplaceAdd(x: X, y: X): Unit
}

trait InplaceAdditiveMonoid[@specialized(Int, Double) X] extends InplaceAdditiveSemigroup[X] with AdditiveMonoid[X] {
  //def inplaceClear(x: X): Unit
}

trait InplaceAdditiveGroup[@specialized(Int, Double) X] extends InplaceAdditiveMonoid[X] with AdditiveGroup[X] {
  def inplaceSub(x: X, y: X): Unit = inplaceAdd(x, neg(y))
}
