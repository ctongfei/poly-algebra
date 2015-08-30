package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Represents a complete metric space.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.7
 */
trait CompleteMetricSpace[-V, @sp(fd) F] extends MetricSpace[V, F]
object CompleteMetricSpace extends BinaryImplicitGetter[CompleteMetricSpace]

/**
 * Represents a Banach space.
 */
trait BanachSpace[V, @sp(fd) F] extends CompleteMetricSpace[V, F] with NormedVectorSpace[V, F]
object BanachSpace extends BinaryImplicitGetter[BanachSpace]

/**
 * Represents a Hilbert space.
 */
trait HilbertSpace[V, @sp(fd) F] extends BanachSpace[V, F] with InnerProductSpace[V, F]
object HilbertSpace extends BinaryImplicitGetter[HilbertSpace]
