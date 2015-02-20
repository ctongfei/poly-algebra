package poly.algebra

/**
 * Typeclass for function spaces.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait FunctionSpace[X, Y, @specialized(Int, Double, Float) R] extends Module[X => Y, R] {

  def module: Module[Y, R]

  def scale(a: R, f: X => Y) = (x: X) => module.scale(a, f(x))

  def neg(f: X => Y) = (x: X) => module.neg(f(x))

  def zero = (x: X) => module.zero

  def add(f: X => Y, g: X => Y) = (x: X) => module.add(f(x), g(x))

  override def sub(f: X => Y, g: X => Y) = (x: X) => module.sub(f(x), g(x))

}

object FunctionSpace {
  implicit def default[X, Y, R](implicit M: Module[Y, R], r: Ring[R]): FunctionSpace[X, Y, R] = new FunctionSpace[X, Y, R] {
    def module = M
    def ringOfScalar = r
  }
}
