package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object specgroup {

  type sp = specialized
  final val d = new Specializable.Group(Tuple1(Double))
  final val i = new Specializable.Group(Tuple1(Int))
  final val b = new Specializable.Group(Tuple1(Boolean))
  final val fd = new Specializable.Group((Float, Double))
  final val di = new Specializable.Group((Int, Double))
  final val fdi = new Specializable.Group((Int, Float, Double))
  final val dil = new Specializable.Group((Int, Double, Long))
  final val fdil = new Specializable.Group((Int, Float, Double, Long))
  final val fdib = new Specializable.Group((Int, Float, Double, Boolean))
  final val dib = new Specializable.Group((Int, Double, Boolean))
  final val ib = new Specializable.Group((Int, Boolean))
  final val il = new Specializable.Group((Int, Long))

}
