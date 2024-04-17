/** our implementation and abstraction are generally coupled to each other in normal
  * inheritance
  *
  * using bridge pattern we can decouple them so they can both change without affecting
  * each other
  *
  * we achieve this feat by creating two separate inheritance hierarchies; one for
  * implementation and another for abstraction
  *
  * we use composition to bridhe these two hierarchies.
  */
