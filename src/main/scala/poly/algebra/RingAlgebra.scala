package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait RingAlgebra[V, @specialized(Int, Double, Float) R] extends Module[V, R] with Ring[V]
