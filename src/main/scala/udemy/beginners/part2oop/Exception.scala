package udemy.beginners.part2oop

/**
 * Created with IntelliJ IDEA.
 * User: ngupta
 * Date: 20/4/2020 AD
 * Time: 21:11
 */
object Exception extends App {
  val x: String = null

  // this will throw NullPointerException
  // println(x.length)

  // 1. throwing exceptions
  //  val aWeirdValue: String = throw new NullPointerException

  // throwable classes extends Throwable class.
  // Exception and Error are the major Throwable subtypes


  // 2. catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you")
    else 42

  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => println("Runtime exception occurred")
  } finally {
    // this code will executed No Matter What
    // optional
    // does not influence result
    println("finally")
  }

  println(potentialFail)

  // 3. how to define custom exception for your application-type
  class MyException extends Exception

  val exception = new MyException

  //  throw exception

  /*
     1. Crash your program without an OutOfMemoryException
     2. Crash with SOError
     3. PocketCalculator
        - add(x, y)
        - subtract(x, y)
        - multiply(x, y)
        - divide(x, y)


   */

  // OutOfMemoryError
  //  val array = Array.ofDim(Int.MaxValue)

  // SOError
  //  def infinite: Int = 1 + infinite
  //  val noLimit = infinite


  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  class MathCalculationException extends RuntimeException("Divide by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new OverflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      x / y
    }
  }

  //  println(PocketCalculator.add(Int.MaxValue, 1))
  //  println(PocketCalculator.subtract(Int.MinValue, 1))
  //  println(PocketCalculator.multiply(Int.MaxValue / 2, 4))
  //  println(PocketCalculator.divide(Int.MinValue, 0))

}
