package poly.algebra.std

import java.time._
import poly.algebra._

/**
 * Represents the natural affine structure on time instants.
 * @author Tongfei Chen
 * @since 0.2.10
 */
object InstantStructure extends AffineSpace[Instant, Duration, Long] with Order[Instant] {

  override def eq(x: Instant, y: Instant) = x equals y
  def cmp(x: Instant, y: Instant) = x compareTo y
  def translate(x: Instant, k: Duration) = x plus k
  def sub(x: Instant, y: Instant) = Duration.between(y, x)
  def vectorSpaceOnVector = DurationStructure

}

object DurationStructure extends VectorSpace[Duration, Long] with Order[Duration] {

  override def eq(x: Duration, y: Duration) = x equals y
  def cmp(x: Duration, y: Duration): Int = x compareTo y

  def scale(x: Duration, k: Long) = x multipliedBy k
  def add(x: Duration, y: Duration) = x plus y
  override def neg(x: Duration): Duration = x.negated
  override def sub(x: Duration, y: Duration) = x minus y
  def zero = Duration.ZERO

  /** This is not really a field; but we could pretend that it is (consider them as real numbers). */
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
