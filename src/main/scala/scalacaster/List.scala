package scalacaster

/**
 * Created by ngupta on 24/8/2019 AD.
 */
abstract sealed class List {
  def head: Int

  def tail: List

  def isEmpty: Boolean
}

case object Nil extends List {
  override def head: Int = ??? //fail("Empty list")

  override def tail: List = ??? //fail("Empty list")

  override def isEmpty: Boolean = true
}

case class Cons(head: Int, tail: List = Nil) extends List {

  override def isEmpty: Boolean = false

}
