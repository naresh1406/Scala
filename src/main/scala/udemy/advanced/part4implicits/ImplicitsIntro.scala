package udemy.advanced.part4implicits

/**
 * Created with IntelliJ IDEA.
 * User: ngupta
 * Date: 13/4/2020 AD
 * Time: 16:36
 */
object ImplicitsIntro extends App {
  val pair = "Daniel" -> "555"
  val intPair = 1 -> 2

  case class Person(name: String) {
    def greet = s"Hello, I'm $name"
  }

  implicit def fromStringToPerson(str: String) = Person(str)

  // Though there is no greet method in String class how below code is not giving warning or error.
  // Internally compiler look for if there any implicit converter of string which have greet method.
  // Compiler will convert --> fromStringToPerson("Peter").greet
  println("Peter".greet)

  // if there are redundancy in greet method compiler will through error.

  /*class A {
    def greet: Int = 2
  }

  implicit def fromStringToA(str: String): A = new A*/

  // implicits parameters
  def increment(x: Int)(implicit amount: Int) = x + amount

  implicit val defaultAmount: Int = 10

  // compiler will find the implicit and supply it is not same as Default arg
  println(increment(2))


}
