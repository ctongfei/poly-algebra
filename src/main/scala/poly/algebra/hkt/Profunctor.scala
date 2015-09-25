package poly.algebra.hkt

import poly.algebra.factory._

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.9
 */
trait Profunctor[⇾[_, _]] { self =>

  def map[X, Y, Z](f: X ⇾ Y)(g: Y => Z): X ⇾ Z

  def contramap[X, Y, Z](g: Y ⇾ Z)(f: X => Y): X ⇾ Z

  def contravariantFunctor[Z]: ContravariantFunctor[({type λ[α] = α ⇾ Z})#λ] = new ContravariantFunctor[({type λ[α] = α ⇾ Z})#λ] {
    def contramap[Y, X](mx: Y ⇾ Z)(f: X => Y) = self.contramap(mx)(f)
  }

  def functor[X]: Functor[({type λ[β] = X ⇾ β})#λ] = new Functor[({type λ[β] = X ⇾ β})#λ] {
    def map[Y, Z](mx: X ⇾ Y)(f: Y => Z) = self.map(mx)(f)
  }

}

object Profunctor extends BinaryImplicitHktGetter[Profunctor]
