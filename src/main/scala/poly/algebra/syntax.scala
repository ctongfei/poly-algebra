package poly.algebra

import poly.algebra.function._

/**
  * An object for all-in-one importing experience.
  * Once imported this `syntax` object,
  * `implicits` / `ops` / `functions` should '''NO LONGER''' be imported.
  * @author Tongfei Chen
  * @since 0.2.15
  */
object syntax extends GenericFunctions with MapReduceMathOps with ImplicitStructures with Priority1Implicits
