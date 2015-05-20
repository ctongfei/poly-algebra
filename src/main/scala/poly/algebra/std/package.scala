package poly.algebra

/**
 * This package contains
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object std {

  private[std] type ZZ = Int
  private[std] type ZL = Long
  private[std] type ZS = Short
  private[std] type ZB = Byte

  private[std] type RR = Double
  private[std] type RF = Float

  private[std] type BB = Boolean

  implicit def seqMonoid[X]: Monoid[Seq[X]] = new Monoid[Seq[X]] {
    def id = Seq()
    def op(x: Seq[X], y: Seq[X]) = x ++ y
  }

}
