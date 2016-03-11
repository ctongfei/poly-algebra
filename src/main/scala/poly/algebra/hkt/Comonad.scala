package poly.algebra.hkt

import poly.algebra.factory._

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * @author Tongfei Chen
 * @since 0.2.4
 */
trait Comonad[W[_]] extends Functor[W] { self =>
  
  def id[X](u: W[X]): X

  def extend[X, Y](wx: W[X])(f: W[X] => Y): W[Y]

  def map[X, Y](wx: W[X])(f: X => Y): W[Y] = extend(wx)(u => f(id(u)))

  def duplicate[X](wx: W[X]): W[W[X]] = extend(wx)(u => u)

  /**
   * Returns the CoKleisli category naturally associated with this monad, i.e.
   * a category on the type `W[A] => B`.
   * @return The natural CoKleisli category
   */
  def CoKleisli: Arrow[({type λ[α, β] = W[α] => β})#λ] = new Arrow[({type λ[α, β] = W[α] => β})#λ] {
    def compose[X, Y, Z](g: W[Y] => Z, f: W[X] => Y) = wx => g(extend(wx)(f))
    def id[X] = self.id[X]
    def lift[X, Y](f: X => Y) = wx => f(id(wx))
    def apply1[X, Y, Z](f: W[X] => Y) = wxz => (f(self.map(wxz)(_._1)), id(wxz)._2)
    def apply2[X, Y, Z](f: W[X] => Y) = wzx => (id(wzx)._1, f(self.map(wzx)(_._2)))
  }

}

object Comonad extends ImplicitHktGetter[Comonad]
