package scalacaster.primitive

/**
 * Created by ngupta on 24/8/2019 AD.
 */
object Number extends App {

  def EPS = 1e-5

  def sqrt(x: Double): Double = {
    def loop(y: Double): Double =
      if (math.abs(y * y - x) / x > EPS) loop(((x / y) + y) / 2.0)
      else y

    loop(1.0)
  }

  def power(x: Double, y: Int): Double = {
    def loop(xx: Double, acc: Double, yy: Int): Double =
      if (yy == 0) acc
      else if (yy % 2 == 0) loop(xx * xx, acc, yy / 2)
      else loop(xx, acc * xx, yy - 1)

    loop(x, 1.0, y)
  }

  def gcd(x: Int, y: Int): Int =
    if (y == 0) x else gcd(y, x % y)

  def lcm(x: Int, y: Int): Int = math.abs(x * y) / gcd(x, y)

  def isPowerOfTwo(x: Int): Boolean =
    (x & (x - 1)) == 0

  def isSnoob(x: Int, y: Int): Boolean =
    if (x == 0 && y == 0) true
    else if (x == 0 || y == 0) false
    else if (x % 2 == 1 && y % 2 == 0) isSnoob(x, y >> 1)
    else if (x % 2 == 0 && y % 2 == 1) isSnoob(x >> 1, y)
    else isSnoob(x >> 1, y >> 1)

  def fibonacci(n: Int): Int =
    if (n == 0 || n == 1) 1
    else fibonacci(n - 1) + fibonacci(n - 2)

  def fibonacciIter(n: Int): Int = {
    def loop(a: Int, b: Int, k: Int): Int =
      if (k > 0) loop(b, a + b, k - 1)
      else b
    loop(0, 1, n)
  }

  def fibonacciMat(n: Int): Int = {
    def loop(a: Int, b: Int, c: Int, d: Int, n: Int,
             e: Int, f: Int, g: Int, h: Int): Int =
      if (n == 0) f
      else if (n % 2 != 0)
        loop(a * a + b * c, a * b + b * d, c * a + d * c, c * b + d * d, n / 2,
          e * a + g * b, f * a + h * b, e * c + g * d, f * c + h * d)
      else
        loop(a * a + b * c, a * b + b * d, c * a + d * c, c * b + d * d, n / 2,
          e, f, g, h)

    loop(1, 1, 1, 0, n + 1, 1, 0, 0, 1)
  }


  (1 to 10).foreach(x => println(sqrt(x)))
  (1 to 10).foreach(x => println(power(x, x)))
  (1 to 5).foreach(x => (1 to 5).foreach(y => print(gcd(x, y) + ", ")))
  println()
  (1 to 5).foreach(x => (1 to 5).foreach(y => print(lcm(x, y) + ", ")))
  println()
  (1 to 10).foreach(x => print(isPowerOfTwo(x) + ", "))
  println()
  (1 to 5).foreach(x => (1 to 5).foreach(y => print(isSnoob(x, y) + ", ")))
  println()
  (1 to 10).foreach(x => print(fibonacci(x) + ", "))
  println()
  (1 to 10).foreach(x => print(fibonacciIter(x) + ", "))
  println()
  (1 to 10).foreach(x => print(fibonacciMat(x) + ", "))
}
