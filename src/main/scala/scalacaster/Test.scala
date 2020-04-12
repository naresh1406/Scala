package scalacaster

/**
 * Created by ngupta on 24/8/2019 AD.
 */
object Test extends App {

  case class Book(title: String, pages: Int)

  val books = Seq(Book("Future of Scala developers", 85),
    Book("Parallel algorithms", 240),
    Book("Object Oriented Programming", 130),
    Book("Mobile Development", 495))
  //Book(Mobile Development,495)
  println(books.maxBy(book => book.pages))
  //Book(Future of Scala developers,85)
  println(books.minBy(book => book.pages))

  val numbers = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(x => x % 2 == 0)
  println(numbers)

  println(books.filter(book => book.pages > 120))


  val abcd = Seq('a', 'b', 'c', 'd')
  val efgj = Seq('e', 'f', 'g', 'h')
  val ijkl = Seq('i', 'j', 'k', 'l')
  val mnop = Seq('m', 'n', 'o', 'p')
  val qrst = Seq('q', 'r', 's', 't')
  val uvwx = Seq('u', 'v', 'w', 'x')
  val yz = Seq('y', 'z')
  val alphabet = Seq(abcd, efgj, ijkl, mnop, qrst, uvwx, yz)
  //
  // List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t,
  //      u, v, w, x, y, z)
  println(alphabet.flatten)

  //List(2, 4, 6, 8, 10, 12)
  println(numbers.map(n => n * 2))

  val chars = Seq('a', 'b', 'c', 'd')
  //List(A, B, C, D)
  println(chars.map(ch => ch.toUpper))

  var nu = null

}
