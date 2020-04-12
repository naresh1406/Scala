package udemy.advanced.part2afp.Test

/**
 * Created by ngupta on 15/10/2019 AD.
 */
object Test extends App {
  val localExc = 0.14571000
  val displayExc = 0.03265300
  val uplift = 1.015

  for(a <- 1 to 1000) {
    println(a + ", " + (a*displayExc*uplift)/localExc)
  }
}