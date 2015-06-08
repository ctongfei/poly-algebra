package poly.algebra.macroimpl

import poly.algebra._
import poly.util.fastloop._
import scala.language.experimental.macros
import scala.reflect.macros.blackbox._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object MapReduceMacroImpl {

  def sumOp[T, Ev](c: Context)(n: c.Expr[Int])(f: c.Expr[Int => T])(ev: c.Expr[Ev]) = {
    import c.universe._
    val tree = q"""
        var _poly_cumul = $ev.zero
        var _poly_index = 0
        while (_poly_index < $n) {
          _poly_cumul = $ev.add(_poly_cumul, $f(_poly_index))
          _poly_index += 1
        }
        _poly_cumul
    """
    new InlineUtil[c.type](c).inlineAndReset[T](tree)
  }

  def productOp[T, Ev](c: Context)(n: c.Expr[Int])(f: c.Expr[Int => T])(ev: c.Expr[Ev]) = {
    import c.universe._
    val tree = q"""
        var _poly_cumul = $ev.one
        var _poly_index = 0
        while (_poly_index < $n) {
          _poly_cumul = $ev.mul(_poly_cumul, $f(_poly_index))
          _poly_index += 1
        }
        _poly_cumul
    """
    new InlineUtil[c.type](c).inlineAndReset[T](tree)
  }

  def forAllOp[T, Ev](c: Context)(n: c.Expr[Int])(f: c.Expr[Int => T])(ev: c.Expr[Ev]) = {
    import c.universe._
    val tree = q"""
        var _poly_cumul = $ev.one
        var _poly_index = 0
        while (_poly_index < $n) {
          _poly_cumul = $ev.and(_poly_cumul, $f(_poly_index))
          _poly_index += 1
        }
        _poly_cumul
    """
    new InlineUtil[c.type](c).inlineAndReset[T](tree)
  }

  def existsOp[T, Ev](c: Context)(n: c.Expr[Int])(f: c.Expr[Int => T])(ev: c.Expr[Ev]) = {
    import c.universe._
    val tree = q"""
        var _poly_cumul = $ev.zero
        var _poly_index = 0
        while (_poly_index < $n) {
          _poly_cumul = $ev.or(_poly_cumul, $f(_poly_index))
          _poly_index += 1
        }
        _poly_cumul
    """
    new InlineUtil[c.type](c).inlineAndReset[T](tree)
  }


}
