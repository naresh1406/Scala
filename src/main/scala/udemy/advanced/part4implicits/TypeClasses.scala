package udemy.advanced.part4implicits

/**
 * Created with IntelliJ IDEA.
 * User: ngupta
 * Date: 13/4/2020 AD
 * Time: 20:07
 */
object TypeClasses extends App {

  // Type Check
  trait HtmlSerializer[T] {
    def serialize(value: T): String
  }

  object HtmlSerializer {
    def serializer[T](value: T)(implicit serializer: HtmlSerializer[T]): String = serializer.serialize(value)

    def apply[T](implicit serializer: HtmlSerializer[T]) = serializer
  }

  implicit object IntSerializer extends HtmlSerializer[Int] {
    override def serialize(value: Int): String = s"<div>${value}</div>"
  }

  println(HtmlSerializer[Int].serialize(42))

  case class User(name: String, age: Int, email: String)

  implicit object UserSerializer extends HtmlSerializer[User] {
    override def serialize(user: User): String = s"<div>${user.name} (yo ${user.age}) <a href=${user.email}></div>"
  }

  val john = User("John", 22, "john@agoda.com")
  // We can have access to entire type class methods
  println(HtmlSerializer.serializer[User](john))


  trait Equal[T] {
    def apply(a: T, b: T): Boolean
  }

  object Equal {
    def apply[T](a: T, b: T)(implicit equalizer: Equal[T]): Boolean =
      equalizer.apply(a, b)
  }

  implicit object NameEquality extends Equal[User] {
    override def apply(a: User, b: User): Boolean = a.name == b.name
  }

  object FullEquality extends Equal[User] {
    override def apply(a: User, b: User): Boolean = a.name == b.name && a.email == b.email
  }

  val anotherJohn = User("John", 45, "anotherjohn@agoda.com")

  println(Equal.apply(john, anotherJohn))

}
