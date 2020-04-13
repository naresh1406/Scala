package udemy.advanced.part4implicits

/**
 * Created with IntelliJ IDEA.
 * User: ngupta
 * Date: 13/4/2020 AD
 * Time: 17:33
 */
object OrganizingImplicits extends App {
  val numbers = List(1, 4, 2, 5, 3)
  println(numbers.sorted) // natural ordering implicits from scala.Predef is used

  implicit val reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  println(numbers.sorted) // reverseOrdering implicits will overwrite scala.Predef implicits

  //  If we add multiple implicits compiler will through error of ambiguity
  //  implicit val naturalOrdering: Ordering[Int] = Ordering.fromLessThan(_ < _)


  /*
    Implicits (used as implicit parameters)
      - val/var
      - object
      - accessor methods = defs with no parenthesis
  */

  case class Person(name: String, age: Int)

  val persons = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("Jerry", 30),
    Person("John", 66)
  )

  //  println(persons.sorted) // will through error if we don't define implicit for person ordering

  //  implicit val orderingByName: Ordering[Person] = Ordering.fromLessThan[Person](_.name < _.name)
  //  implicit val orderingByAge: Ordering[Person] = Ordering.fromLessThan[Person](_.age < _.age)
  //    implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name < b.name)
  //  implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => if (a.age == b.age) a.name < b.name else a.age < b.age)
  //  println(persons.sorted)

  /*
    Implicit scope
    - normal scope = LOCAL SCOPE
    - imported scope
    - companion of all types involved in the method signature
      - List
      - Ordering
      - all the types involved = A or any supertype
  */

  object AlphabeticNameOrdering {
    implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name < b.name)
  }

  implicit val orderingByAge: Ordering[Person] = Ordering.fromLessThan(_.age < _.age) // this implicit will take over above one

  object AgeOrdering {
    implicit val orderingByAge: Ordering[Person] = Ordering.fromLessThan(_.age < _.age)
  }

  import AgeOrdering._

  println(persons.sorted)

  /*
    Exercise

    - totalPrice = most used (50%)
    - by unit count
    - by unit price

   */

  case class Purchase(nUnits: Int, unitPrice: Double)

  val purchaseList = List(
    Purchase(2, 4),
    Purchase(4, 2),
    Purchase(3, 5),
    Purchase(5, 3),
    Purchase(2, 5)
  )

  //  println(purchaseList) // Not sorted purchases

  object TotalPriceOrdering {
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => (a.nUnits * a.unitPrice) < (b.nUnits * b.unitPrice))
  }

  object UnitCountOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.nUnits < b.nUnits)
  }

  object UnitPriceOrdering {
    implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.unitPrice < b.unitPrice)
  }

  import TotalPriceOrdering.totalPriceOrdering
    println(purchaseList.sorted)
}
