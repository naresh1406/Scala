package basics.ds

import scala.collection.immutable.TreeMap

/**
 * Created with IntelliJ IDEA.
 * User: ngupta
 * Date: 21/4/2020 AD
 * Time: 16:04
 */
object MapExamples extends App {
  println("Initialize a Map of id and name")
  val map = Map(2 -> "Gupta", 1 -> "Naresh")
  println("Elements of map is " + map)

  println("\nInitialize a TreeMap of id and name")
  val treeMap = TreeMap(2 -> "Gupta", 1 -> "Naresh")
  println("Elements of sorted key treeMap is " + treeMap)
}
