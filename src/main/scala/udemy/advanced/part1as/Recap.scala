package udemy.advanced.part1as

/**
  * Created by ngupta on 2019-06-29.
  */
object Recap extends App {

  val aList = List(1, 2, 3, 4)

  for (e <- aList) print(e + " ")

  abstract class MyList[+T]

  object MyList

  //case classes
  case class Person(name: String, age: Int)

  val aPotentialFailure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "I caught a exception"
  } finally {
    println("Some Logs")
  }

  //functional programming

  val incrementer = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  incrementer(1)

  val anonymousIncrementer = (x: Int) => x + 1

  val pairs = for {
    char <- List('a', 'b', 'c')
    num <- List(1,2,3)
  } yield char + num

  println(pairs)

  val anOption = Some(2)

  //pattern matching
  val x = 2
  val order = x match {
    case 1 => "First"
    case 2 => "Second"
    case _ => "None"
  }

  println(order)



}
