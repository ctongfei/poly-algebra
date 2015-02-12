package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait FieldAlgebra[V, F] extends VectorSpace[V, F] with RingAlgebra[V, F]
