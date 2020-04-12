package udemy.advanced.part1as

import scala.util.Try

/**
  * Created by ngupta on 2019-06-29.
  */
object DarkSugars extends App {

  // syntax sugar #1: methods with one param
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."

  val description = singleArgMethod {
    println("calling single arg method with sugar style")
    42
  }

  val aTryInstance = Try { // java]s try{...}
    throw new RuntimeException
  }

  List(1, 2, 3, 4).map { x =>
    x + 1
  }


  // syntax sugar #2: single abstract method
  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1

  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello, scala")
  })

  val aSweeterThread = new Thread(() => println("sweet, scala"))

  abstract class AnAbstractType {
    def implemented: Int = 1

    def f(a: Int): Unit
  }

  val anAbstractInstance: AnAbstractType = (a: Int) => println("sweet, abstract")

  // syntax sugar #3: the :: and #:: methods are special

  val prependList = 2 :: List(3, 4)
  println(prependList)

  println(1 :: 2 :: 3 :: List(4, 5))
  println(List(4, 5).::(3).::(2).::(1))

  // syntax sugar #4: multi-word method naming

  class TeenGirl(name: String) {
    def `and then said`(gossip: String) = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "Scala is so sweet!"

  // syntax sugar #5 infix types
  class Composite[A, B]

  val composite: Int Composite String = ???

  class -->[A, B]

  val towards: Int --> String = ???


  // syntax sugar #6: update() is very special, much like apply()
  val anArray = Array(1, 2, 3)
  anArray(2) = 7 // anArray.update(2, 7)
  // used in mutable collection
  // remember apply() AND update

  // syntax sugar #7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0 // private for OO encapsulation
    def member = internalMember // "getter"
    def member_=(value: Int): Unit =
      internalMember = value // "setter"
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewrittern as aMutableContainer.member_=(42)

}
