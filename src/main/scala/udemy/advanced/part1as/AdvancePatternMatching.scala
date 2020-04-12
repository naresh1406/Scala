package udemy.advanced.part1as

/**
  * Created by ngupta on 2019-06-30.
  */
object AdvancePatternMatching extends App {

  class Person(val name: String, val age: Int)

  object Person {
    def unapply(person: Person): Option[(String, Int)] = Some((person.name, person.age))
  }

  val bob = new Person("bob", 25)
  val greeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
  }
  println(greeting)

  object even {
    def unapply(arg: Int): Option[Boolean] = {
      if (arg % 2 == 0) Some(true)
      else None
    }
  }

  object singleDigit {
    def unapply(arg: Int): Option[Boolean] = {
      if (n < 10) Some(true)
      else None
    }
  }


  val n = 45
  val mathProperty = n match {
    case singleDigit(n) => "Single Digit"
    case even(n) => "An even number"
    case _ => "No property"
  }

}
