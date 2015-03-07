package poly.algebra

/**
 * This package contains support for monadic typeclasses.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object monad {

  implicit class withMonadicOps[M[_], X](val x: M[X]) extends AnyVal {
    def >>=[Y](f: X => M[Y])(implicit M: Monad[M]) = M.flatMap(x)(f)
  }

}
