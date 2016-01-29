package poly.algebra.law

import org.scalacheck.Prop._
import org.scalacheck._
import poly.algebra._
import poly.algebra.implicits._
import poly.algebra.function._
import poly.algebra.ops._

/**
 * Law checking for Poly-algebra structures using ScalaCheck.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.3.0
 */
object Law {

  private implicit class withImplies(val x: Boolean) extends AnyVal {
    def implies(y: Boolean) = !x || y
  }

  def associativity[X: Semigroup: Arbitrary] = forAll { (x: X, y: X, z: X) =>
    ((x op y) op z) == (x op (y op z))
  }

  def identity[X: Monoid: Arbitrary] = forAll { (x: X) =>
    (id[X] op x) == x && (x op id[X]) == x
  }

  def divisibility[X: Group: Arbitrary] = forAll { (x: X) =>
    (x op implicitly[Group[X]].inv(x)) == id[X]
  }

  def commutativity[X: Semigroup: Arbitrary] = forAll { (x: X, y: X) =>
    (x op y) == (y op x)
  }

  def additionAssociativity[X: AdditiveSemigroup : Arbitrary] = forAll { (x: X, y: X, z: X) =>
    (x + y) + z == x + (y + z)
  }

  def additionIdentity[X: AdditiveMonoid: Arbitrary] = forAll { (x: X) =>
    (x + zero[X]) == x && (zero[X] + x) == x
  }

  def additionInverse[X: AdditiveGroup: Arbitrary] = forAll { (x: X) =>
    x + -x == zero[X]
  }

  def additionCommutativity[X: AdditiveSemigroup : Arbitrary] = forAll { (x: X, y: X) =>
    x + y == y + x
  }

  def eqSymmetry[X: Equiv : Arbitrary] = forAll { (x: X, y: X) =>
    (x === y implies y === x) && ((x !== y) implies (y !== x))
  }

  def orderReflexivity[X: PartialOrder : Arbitrary] = forAll { (x: X) =>
    x <= x && x >= x
  }

  def orderAntisymmetry[X: PartialOrder : Arbitrary] = forAll { (x: X, y: X) =>
    (x <= y && y >= x) implies (x === y)
  }

  def orderTransitivity[X: PartialOrder : Arbitrary] = forAll { (x: X, y: X, z: X) =>
    (x <= y && y <= z) implies (x <= z)
  }

  def semigroup[X: Semigroup : Arbitrary] = associativity
  def monoid[X: Monoid: Arbitrary] = associativity && identity
  def group[X: Group: Arbitrary] = associativity && identity && divisibility
  def additiveSemigroup[X: AdditiveSemigroup: Arbitrary] = additionAssociativity[X]
  def additiveMonoid[X: AdditiveMonoid: Arbitrary] = additionAssociativity[X] && additionIdentity[X]
  def additiveGroup[X: AdditiveGroup: Arbitrary] = additionAssociativity[X] && additionIdentity[X] && additionInverse[X]
  def ring[X: Ring: Arbitrary] = additionAssociativity[X] && additionCommutativity[X]

  def partialOrder[X: PartialOrder : Arbitrary] = orderReflexivity[X] && orderAntisymmetry[X] && orderTransitivity[X]

  def bijection[X: Arbitrary, Y: Arbitrary](implicit b: Bijection[X, Y]) = forAll { (x: X, y: Y) =>
    (b.invert(b(x)) == x) && (b(b.invert(y)) == y)
  }
}
