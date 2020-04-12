package scalacaster.collection

/**
 * Created by ngupta on 24/8/2019 AD.
 */
abstract sealed class List[+A] {
  def head: A

  def tail: List[A]

  def isEmpty: Boolean

  def append[B >: A](x: B): List[B] =
    if (isEmpty) List.make(x)
    else List.make(head, tail.append(x))

  def prepend[B >: A](x: B): List[B] = List.make(x, this)

  def concat[B >: A](xs: List[B]): List[B] =
    if (isEmpty) xs
    else tail.concat(xs).prepend(head)

  def remove[B >: A](x: B): List[B] =
    if (isEmpty) fail("Can't find " + x + " in the list")
    else if (x != head) List.make(head, tail.remove(x))
    else tail

  def apply(n: Int): A =
    if (isEmpty) fail("Index out of bound")
    else if (n < 0) fail("Index (< 0) out of bounds")
    else if (n == 0) head
    else tail(n - 1)

  def fail(m: String) = throw new NoSuchElementException(m)
}


case object Nil extends List[Nothing] {
  def head: Nothing = fail("An empty list.")

  def tail: List[Nothing] = fail("An empty list.")

  def isEmpty: Boolean = true
}

case class Cons[A](head: A, tail: List[A]) extends List[A] {
  def isEmpty: Boolean = false
}

object List extends App {

  def empty[A]: List[A] = Nil

  def make[A](x: A, t: List[A] = Nil): List[A] = Cons(x, t)

  def apply[A](xs: A*): List[A] = {
    var r: List[A] = List.empty
    for (x <- xs.reverse) r = r.prepend(x)
    r
  }
}
