package poly.algebra.macroimpl

import scala.reflect.macros.blackbox._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object MacroImpl {

  def ops(c: Context)(s: String): c.TermName = {
    import c.universe._
    TermName(s)
  }

  def opsName(c: Context)(op: c.TermName): c.TermName = {
    import c.universe._
    op match {

      case TermName("$hash$hash$hash") => TermName("hash")

      case TermName("$eq$eq$eq") => TermName("eq")
      case TermName("$eq$bang$eq") => TermName("ne")
      case TermName("$greater") => TermName("gt")
      case TermName("$greater$eq") => TermName("ge")
      case TermName("$less") => TermName("lt")
      case TermName("$less$eq") => TermName("le")
      case TermName("$eq$tilde$eq") => TermName("tied")

      case TermName("unary_$minus") => TermName("neg")

      case TermName("$plus") => TermName("add")
      case TermName("$minus") => TermName("sub")
      case TermName("$times") => TermName("mul")
      case TermName("$times$times") => TermName("pow")

      case TermName("$div") => TermName("quot")
      case TermName("$percent") => TermName("mod")
      case TermName("$div$percent") => TermName("quotmod")

      case TermName("$amp") => TermName("and")
      case TermName("bar") => TermName("or")
      case TermName("$up") => TermName("xor")
      case TermName("unary_$bang") => TermName("not")

      case _ => op
    }
  }

  /** -lhs =COMPILER=> implicitConv(lhs)(ev).unary_-() =MACRO=> ev.unary_-(lhs) */
  /**
   * Rewrites a unary operator by implicit conversion into a direct call.
   */
  def unaryOp[T, Ev](c: Context)(ev: c.Expr[Ev]) = {
    import c.universe._
    c.macroApplication match {
      case q"$implicitConv($lhs).$method($evidence)" => q"$evidence.${opsName(c)(method)}($lhs)"
    }
  }

  /**
   * Rewrites a binary operator by implicit conversion into a direct call.
   */
  def binaryOp[T1, T2, Ev](c: Context)(y: c.Expr[T2])(ev: c.Expr[Ev]) = {
    import c.universe._
    c.macroApplication match {
      case q"$implicitConv($lhs).$method($rhs)($evidence)" => q"$evidence.${opsName(c)(method)}($lhs, $rhs)"
    }
  }

  def ipowOp[T, Ev](c: Context)(n: c.Expr[Int])(ev: c.Expr[Ev]) = {
    import c.universe._
    c.macroApplication match {
      case q"$implicitConv($lhs).$method($rhs)($evidence)" => q"$evidence.${ops(c)("ipow")}($lhs, $rhs)"
    }
  }


}

