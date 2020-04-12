package udemy.advanced.part2afp

/**
  * Created by ngupta on 2019-06-30.
  */
object PartialFunction extends App {

  val aFunction = (x: Int) => x + 1

  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }
  // {1, 2, 5} => Int

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(aPartialFunction(1))
  //  println(aPartialFunction(198765))

  // PF Utilities
  println(aPartialFunction.isDefinedAt(65))

  // lift
  val lifted = aPartialFunction.lift // Int => Option[Int]
  println(lifted(1))
  println(lifted(4))

  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(pfChain(2))
  println(pfChain(45))

  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(x: Int): Int = x match {
      case 1 => 42
      case 2 => 67
      case 5 => 999
    }

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 5
  }

  val chatbot: PartialFunction[String, String] = {
    case "hello" => "Hi my name is HAL9999"
    case "goodbye" => "Once you start talking to me, there is no way to return"
    case "call" => "Unable to find your number"
  }

//  scala.io.Source.stdin.getLines().foreach(line => println(chatbot(line)))
  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)



}
