package poly.algebra

trait PowerOps[@miniboxed X] {
  def root(x: X, n: Int): X
  def sqrt(x: X): X = root(x, 2)
  def cbrt(x: X): X = root(x, 3)
  def pow(x: X, y: X): X
}
