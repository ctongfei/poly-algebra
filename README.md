### poly-algebra
`Poly-algebra` is a library that contains typeclass abstractions for common algebraic structures. Implicits, specialization
and macros are used extensively to create an elegant API with virtually no performance cost.

 - New in 0.2.0: Macros for inlining implicit operators

### Supported typeclasses

#### Equality and Hashing functions
|      | === | =!= | ### |
|:----:|:---:|:---:|:---:|
|  Eq  |  *  |  *  |     |
| Hash |  *  |  *  |  *  |

#### Existence of identity elements
|             | id | zero | one |
|:-----------:|:--:|:-:|:-:|
| HasIdentity |  * |   |   |
|   HasZero   |    | * |   |
| HasOne      |    |   | * |

#### Symbol-agnostic structures (Semigroup/Monoid/Group)

|           | id | op | inv | combineN |
|:---------:|:--:|:--:|-----|:--------:|
| Semigroup |    | *  |     | *        |
| Monoid    | *  | *  |     | *        |
| Group     | *  | *  |     | *        |

### Groups/Rings/Fields

|                         | zero | one | add + | neg unary_- | sub - | mul * | inv | div / | sumN | ipow ** | mod % | gcd | lcm |
|:-----------------------:|:----:|:---:|:-----:|:-----:|:-----:|:-----:|:---:|:-----:|:----:|:-------:|-------|-----|-----|
|    AdditiveSemigroup    |      |     |   *   |       |       |       |     |       |   *  |         |       |     |     |
|      AdditiveMonoid     |   *  |     |   *   |       |       |       |     |       |   *  |         |       |     |     |
|      AdditiveGroup      |   *  |     |   *   |   *   |   *   |       |     |       |   *  |         |       |     |     |
| MultiplicativeSemigroup |      |     |       |       |       |   *   |     |       |      |    *    |       |     |     |
|   MultiplicativeMonoid  |      |  *  |       |       |       |   *   |     |       |      |    *    |       |     |     |
|   MultiplicativeGroup   |      |  *  |       |       |       |   *   |  *  |   *   |      |    *    |       |     |     |
| Semiring                | *    | *   | *     |       |       | *     |     |       | *    | *       |       |     |     |
| Ring                    | *    | *   | *     | *     | *     | *     |     |       | *    | *       |       |     |     |
| EuclideanDomain         | *    | *   | *     | *     | *     | *     |     | *     | *    | *       | *     | *   | *   |
| Field                   | *    | *   | *     | *     | *     | *     | *   | *     | *    | *       | *     | *   | *   |

#### Orders/Lattices/Boolean algebra

`PartialOrder`, `UpperSemilattice`, `LowerSemilattice`, `Lattice`, `WeakOrder`, `TotalOrder`, `BooleanAlgebra`

#### Modules/Algebras/Vector spaces

`Module`, `AlgebraOverRing`, `AlgebraOverField`

`VectorSpace`, `MetricSpace`, `NormedVectorSpace`, `InnerProductSpace`

#### Function spaces

`FunctionSpace`, `EndofunctionSpace`

### Typeclasses for the abstraction of elementary functions

`SignOps`, `PowerOps`, `TrigExpOps`
