package xmath.algebra

trait Power[@specialized(Double, Float) X] {
  def root(x: X, n: Int): X
  def sqrt(x: X): X = root(x, 2)
  def cbrt(x: X): X = root(x, 3)
  def pow(x: X, y: X): X
}