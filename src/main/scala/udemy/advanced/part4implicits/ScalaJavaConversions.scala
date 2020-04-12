package udemy.advanced.part4implicits

import java.{util => ju}

/**
 * Created with IntelliJ IDEA.
 * User: ngupta
 * Date: 12/4/2020 AD
 * Time: 19:46
 */
object ScalaJavaConversions extends App {

  import collection.JavaConverters._

  val javaSet: ju.Set[Int] = new ju.HashSet[Int]()
  (1 to 5).foreach(javaSet.add)
  println(javaSet)

  val scalaSet = javaSet.asScala
  println(scalaSet)

  import scala.collection.mutable._

  val numberBuffer = ArrayBuffer[Int](1, 2, 3)
  val juNumberBuffer = numberBuffer.asJava

  println(juNumberBuffer.asScala == numberBuffer)

  val numbers = List(1, 2, 3) //immutable
  val juNumbers = numbers.asJava
  val backToScala = juNumbers.asScala
  println(backToScala eq numbers) // false
  println(backToScala == numbers) // true

  /*
      Exercise implement converter java to scala for optional
      Optional -> Option
      asScala method
   */

  class ToScala[T](value: => T) {
    def asScala: T = value
  }

  implicit def asScalaOptional[T](o: ju.Optional[T]): ToScala[Option[T]] = new ToScala[Option[T]](
    if (o.isPresent) Some(o.get()) else None
  )

  val juOptional: ju.Optional[Int] = ju.Optional.of(2)
  val scalaOtion = juOptional.asScala
  println(scalaOtion)

}
