package udemy.basics

/**
  * Created by ngupta on 2019-07-06.
  */
object Basic extends App {

  case class Person(name: String, age: Int, gender: Option[String])

  def fun(person: Person): Unit = {
    println(person.gender.orNull)
    println(person.gender.orElse(Some("Con")))
    println(person.gender.getOrElse(Some("Con")))
  }

  fun(Person("Hello", 1, null))
}
