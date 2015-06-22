package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.1
 */
trait ConcatenativeMonoid[X] extends ConcatenativeSemigroup[X] with HasEmpty[X] { self =>

  override def concatN(x: X, n: Int): X = asMonoidWithConcat.combineN(x, n)

  def asMonoidWithConcat: Monoid[X] = new Monoid[X] {
    def op(x: X, y: X) = self.concat(x, y)
    def id = self.empty
  }

}

object ConcatenativeMonoid {

  def apply[X](implicit M: ConcatenativeMonoid[X]) = M

  def create[X](f: (X, X) => X, emptyElem: X) = new ConcatenativeMonoid[X] {
    def concat(x: X, y: X): X = f(x, y)
    def empty = emptyElem
  }
}
