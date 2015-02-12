package xmath

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object algebra {

  def id[X](implicit I: HasIdentity[X]) = I.id

  def zero[X](implicit Z: HasZero[X]) = Z.zero

  def one[X](implicit O: HasOne[X]) = O.one

  implicit val IntRing = Ring.create[Int](_+_, _*_, 0, 1, -_)

  implicit val DoubleField = Field.create[Double](_+_, _*_, 0.0, 1.0, -_, 1.0/_)


}
