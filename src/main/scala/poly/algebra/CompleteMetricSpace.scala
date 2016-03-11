package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a complete metric space.
 * @author Tongfei Chen
 * @since 0.2.7
 */
trait CompleteMetricSpace[@sp(fd) -V, @sp(fd) F] extends MetricSpace[V, F]
object CompleteMetricSpace extends BinaryImplicitGetter[CompleteMetricSpace]

/**
 * Represents a Banach space.
 */
trait BanachSpace[@sp(fd) V, @sp(fd) F] extends CompleteMetricSpace[V, F] with NormedVectorSpace[V, F]
object BanachSpace extends BinaryImplicitGetter[BanachSpace]

/**
 * Represents a Hilbert space.
 */
trait HilbertSpace[@sp(fd) V, @sp(fd) F] extends BanachSpace[V, F] with InnerProductSpace[V, F]
object HilbertSpace extends BinaryImplicitGetter[HilbertSpace]
