package poly.algebra.std

import java.time._
import poly.algebra._

/**
 * Represents the natural affine structure on time instants.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
object InstantStructure extends AffineSpace[Instant, Duration, Long] with TotalOrder[Instant] {

  def cmp(x: Instant, y: Instant) = x compareTo y

  def translate(k: Duration, x: Instant) = x plus k
  def sub(x: Instant, y: Instant) = Duration.between(y, x)
  def vectorSpaceOnVector = DurationStructure
}

object DurationStructure extends VectorSpace[Duration, Long] with TotalOrder[Duration] {

  //TODO: override
  def cmp(x: Duration, y: Duration): Int = x compareTo y

  def scale(k: Long, x: Duration) = x multipliedBy k
  def add(x: Duration, y: Duration) = x plus y
  override def neg(x: Duration): Duration = x.negated
  override def sub(x: Duration, y: Duration) = x minus y
  def zero = Duration.ZERO

  /** This is not really a field; but we could pretend that it is (thought them as real numbers). */
  implicit object fieldOnScalar extends Field[Long] {
    final val zero: Long = 0l
    final val one: Long = 1l
    def add(x: Long, y: Long) = x + y
    override def neg(x: Long) = -x
    override def sub(x: Long, y: Long) = x - y
    def mul(x: Long, y: Long) = x * y
    def inv(x: Long): Long = 1l / x
    override def div(x: Long, y: Long) = x / y
  }

}
