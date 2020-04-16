package udemy.advanced.part3concurrency

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
 * Created with IntelliJ IDEA.
 * User: ngupta
 * Date: 16/4/2020 AD
 * Time: 13:58
 */
object FuturesPromises extends App {
  def calculateMeaningOfLife: Int = {
    Thread.sleep(2000)
    42
  }

  val aFuture = Future {
    calculateMeaningOfLife // calculate meaning of life
  } // (global) which is passed by compiler

  println(aFuture.value) // Option[Try[Int]]

  println("waiting on the future")

  aFuture.onComplete {
    case Success(value) => println("meaning of life is " + value)
    case Failure(exception) => println("I failed with " + exception)
  } // SOME thread

  Thread.sleep(3000)

}
