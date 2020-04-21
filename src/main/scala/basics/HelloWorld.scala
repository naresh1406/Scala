package basics

/**
  * Created by ngupta on 2019-06-29.
  */
object HelloWorld extends App {
  println("Hello Wold")

  case class Age(a: Int)

  var list = Seq(Age(1), Age(2), Age(3), Age(2), Age(4))
  println(list.head)
  println(list)

  if (list.count(_.a == 2) > 1) {
    println("Yes")
    println(list.dropRight(1))
  }
  println(list)

  println(AcquirerId.SCB.id)

}

object AcquirerId extends Enumeration {
  type AcquirerId = Value
  val SCB = Value(18)
}
