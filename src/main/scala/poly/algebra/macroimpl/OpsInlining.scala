package poly.algebra.macroimpl

import poly.algebra._

import scala.reflect.macros.blackbox._

/**
 * @author Tongfei Chen
 */
object OpsInlining {

  def ops(c: Context)(s: String): c.TermName = {
    import c.universe._
    TermName(s)
  }

  def opsName(c: Context)(op: c.TermName): c.TermName = {
    import c.universe._
    op match {

      case TermName("$eq$eq$eq") => TermName("eq") // ===
      case TermName("$bang$eq$eq") => TermName("ne") // !==
      case TermName("$u2260") => TermName("ne") // ≠
      case TermName("$greater") => TermName("gt") // >
      case TermName("$greater$eq") => TermName("ge") // >=
      case TermName("$u2265") => TermName("ge") // ≥
      case TermName("$less") => TermName("lt") // <
      case TermName("$less$eq") => TermName("le") // <=
      case TermName("$u2264") => TermName("le") // ≤
      case TermName("$greater$qmark$less") => TermName("cmp")

      case TermName("unary_$minus") => TermName("neg")

      case TermName("$plus") => TermName("add")
      case TermName("$minus") => TermName("sub")
      case TermName("$times") => TermName("mul")
      case TermName("$times$times") => TermName("pow")
      case TermName("$div") => TermName("div")
      case TermName("$percent") => TermName("mod")
      case TermName("$div$percent") => TermName("quotmod")

      case TermName("$colon$times") => TermName("scale")
      case TermName("$times$colon") => TermName("scale")

      case TermName("$colon$plus") => TermName("translate")
      case TermName("$plus$colon") => TermName("translate")

      case TermName("$amp") => TermName("and")
      case TermName("$bar") => TermName("or")
      case TermName("$up") => TermName("xor")
      case TermName("unary_$bang") => TermName("not")

      case TermName("$plus$plus") => TermName("concat")

      case TermName("$u22C5") => TermName("dot") // ⋅

      case TermName("$hash$hash$hash") => TermName("hash")

      case _ => op
    }
  }

  def op1(c: Context) = {
    import c.universe._
    c.macroApplication match {
      case q"$implicitConv($lhs)($evidence).$method" => q"$evidence.${opsName(c)(method)}($lhs)"
    }
  }

  def op2(c: Context)(y: c.Tree) = {
    import c.universe._
    c.macroApplication match {
      case q"$implicitConv($lhs)($evidence).$method($rhs)" => q"$evidence.${opsName(c)(method)}($lhs, $rhs)"
      case q"$implicitConv($lhs)($evidence).$method[$ty]($rhs)" => q"$evidence.${opsName(c)(method)}($lhs, $rhs)"
    }
  }

  def op2R(c: Context)(y: c.Tree) = {
    import c.universe._
    c.macroApplication match {
      case q"$implicitConv($lhs)($evidence).$method($rhs)" => q"$evidence.${opsName(c)(method)}($rhs, $lhs)"
      case q"$implicitConv($lhs)($evidence).$method[$ty]($rhs)" => q"$evidence.${opsName(c)(method)}($rhs, $lhs)"
    }
  }

  def op2LiftRight(c: Context)(y: c.Tree) = {
    import c.universe._
    c.macroApplication match {
      case q"$implicitConv($lhs)($evidence).$method($rhs)" => q"$evidence.${opsName(c)(method)}($lhs, $evidence.fromDouble($rhs))"
      case q"$implicitConv($lhs)($evidence).$method[$ty]($rhs)" => q"$evidence.${opsName(c)(method)}($lhs, $evidence.fromDouble($rhs))"
    }
  }

  def ipowOp(c: Context)(n: c.Tree) = {
    import c.universe._
    c.macroApplication match {
      case q"$implicitConv($lhs)($evidence).$method($rhs)" => q"$evidence.${ops(c)("ipow")}($lhs, $rhs)"
    }
  }

  def genericImpl[X](c: Context)(x: c.Expr[X])(isReal: c.Expr[IsReal[X]]): c.Expr[X] = {
    import c.universe._
    c.Expr[X](q"$isReal.fromDouble($x)")
  }

}

