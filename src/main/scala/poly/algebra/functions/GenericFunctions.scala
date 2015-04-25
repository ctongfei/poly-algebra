package poly.algebra.functions

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait GenericFunctions {

  def id[X](implicit ev: HasIdentity[X]) = ev.id

  def zero[X](implicit ev: HasZero[X]) = ev.zero

  def one[X](implicit ev: HasOne[X]) = ev.one

  def inv[X](x: X)(implicit ev: MultiplicativeGroup[X]) = ev.inv(x)


}
