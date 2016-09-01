package poly.algebra

import poly.algebra.factory._

/**
 * Represents a torsor, also known as a principle homogeneous space of a group.
 * @author Tongfei Chen
 * @since 0.3.13
 */
trait Torsor[X, G] extends GroupAction[X, G] {

  def diff(x: X, y: X): G

  def fixIdentity(i: X): Group[X] = new Group[X] {
    def inv(x: X) = act(i, diff(i, x))
    def id = i
    def op(x: X, y: X) = act(x, diff(y, i))
    override def invOp(x: X, y: X) = act(i, diff(x, y))
  }

}

object Torsor extends BinaryImplicitGetter[Torsor] {

  implicit def trivial[G](implicit G: Group[G]): Torsor[G, G] = new Torsor[G, G] {
    def diff(x: G, y: G) = G.invOp(x, y)
    def act(x: G, y: G) = G.op(x, y)
    def actorGroup = G
  }

}

trait AdditiveTorsor[X, G] extends AdditiveGroupAction[X, G] { self =>
  def sub(x: X, y: X): G
  def asTorsorWithAdd: Torsor[X, G] = new Torsor[X, G] {
    def diff(x: X, y: X) = sub(x, y)
    def actorGroup = self.actorGroup.asGroupWithAdd
    def act(x: X, k: G) = translate(x, k)
  }
  def fixIdentity(i: X): AdditiveGroup[X] = new AdditiveGroup[X] {
    def neg(x: X) = translate(i, self.sub(i, x))
    def zero = i
    def add(x: X, y: X) = translate(x, self.sub(y, i))
    override def sub(x: X, y: X) = translate(i, self.sub(x, y))
  }
}

object AdditiveTorsor extends BinaryImplicitGetter[AdditiveTorsor] {
  implicit def trivial[G](implicit G: AdditiveGroup[G]): AdditiveTorsor[G, G] = new AdditiveTorsor[G, G] {
    def sub(x: G, y: G) = G.sub(x, y)
    def actorGroup = G
    def translate(x: G, y: G) = G.add(x, y)
  }
}

trait MultiplicativeTorsor[X, G] extends MultiplicativeGroupAction[X, G] { self =>
  def div(x: X, y: X): G
  def asTorsorWithMul: Torsor[X, G] = new Torsor[X, G] {
    def diff(x: X, y: X) = div(x, y)
    def actorGroup = self.scalarGroup.asGroupWithMul
    def act(x: X, y: G) = scale(x, y)
  }
  def fixIdentity(i: X): MultiplicativeGroup[X] = new MultiplicativeGroup[X] {
    def inv(x: X) = scale(i, self.div(i, x))
    def one = i
    def mul(x: X, y: X) = scale(x, self.div(y, i))
    override def div(x: X, y: X) = scale(i, self.div(y, x))
  }
}

object MultiplicativeTorsor extends BinaryImplicitGetter[MultiplicativeTorsor] {
  implicit def trivial[G](implicit G: MultiplicativeGroup[G]): MultiplicativeTorsor[G, G] = new MultiplicativeTorsor[G, G] {
    def div(x: G, y: G) = G.div(x, y)
    def scalarGroup = G
    def scale(x: G, y: G) = G.mul(x, y)
  }
}