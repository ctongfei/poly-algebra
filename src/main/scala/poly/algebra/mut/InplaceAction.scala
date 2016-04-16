package poly.algebra.mut

/**
 * @author Tongfei Chen
 * @since 0.3.0
 */
trait InplaceAction[X, -S] {

  /** Acts an element on to a state in-place. */
  def actInplace(x: X, s: S)

}

trait InplaceAdditiveAction[X, -S] {

  def addInplace(x: X, s: S)

  def asActionWithAdd: InplaceAction[X, S] = new InplaceAction[X, S] {
    def actInplace(x: X, s: S) = addInplace(x, s)
  }

}
