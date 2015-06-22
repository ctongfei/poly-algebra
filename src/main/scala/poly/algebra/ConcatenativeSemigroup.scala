package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.1
 */
trait ConcatenativeSemigroup[X] { self =>

  /** The concatenation (`++`) operation of this semigroup. */
  def concat(x: X, y: X): X

  /** Computes the sum ''x'' ++ ''x'' ++ ··· ++ ''x'' with ''x'' repeated for ''n'' times. */
  def concatN(x: X, n: Int): X = asSemigroupWithConcat.combineN(x, n)

  /** Casts this structure as a symbol-agnostic semigroup. */
  def asSemigroupWithConcat: Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X) = self.concat(x, y)
  }

}

object ConcatenativeSemigroup {

  /** Retrieves the concatenative additive semigroup associated with the specific type. */
  def apply[X](implicit S: ConcatenativeSemigroup[X]) = S

  /** Creates an concatenative semigroup of the specific type using the `++` operation provided. */
  def create[X](f: (X, X) => X) = new ConcatenativeSemigroup[X] {
    def concat(x: X, y: X) = f(x, y)
  }

}
