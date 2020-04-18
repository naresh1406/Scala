package udemy.advanced.part3concurrency

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}

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

  // mini social network
  case class Profile(id: String, name: String) {
    def poke(anotherProfile: Profile) =
      println(s"${this.name} poking ${anotherProfile.name}")
  }

  object SocialNetwork {
    //database
    val names = Map(
      "fb.id.1-zuck" -> "Mark",
      "fb.id.2-bill" -> "Bill",
      "fb.id.0-dummy" -> "Bummy"
    )

    val friends = Map(
      "fb.id.1-zuck" -> "fb.id.2-bill"
    )

    val random = new Random()

    // API
    def fetchProfile(id: String): Future[Profile] = Future {
      // fetching from the DB
      Thread.sleep(random.nextInt(300))
      Profile(id, names(id))
    }

    def fetchBestFriend(profile: Profile): Future[Profile] = Future {
      Thread.sleep(random.nextInt(300))
      val bfId = friends(profile.id)
      Profile(bfId, names(bfId))
    }
  }

  // client mark to poke bill
  val mark = SocialNetwork.fetchProfile("fb.id.1-zuck")
  mark.onComplete {
    case Success(markProfile) => {
      val bill = SocialNetwork.fetchBestFriend(markProfile)
      bill.onComplete {
        case Success(billProfile) => markProfile.poke(billProfile)
        case Failure(e) => e.printStackTrace()
      }
    }
    case Failure(e) => e.printStackTrace()
  }

  // Functional decomposition of futures
  // map, flatMap, filter

  val nameOfTheWall = mark.map(profile => profile.name)

  val markBestFriend = mark.flatMap(profile => SocialNetwork.fetchBestFriend(profile))

  val zucksBestFriendRestricted = markBestFriend.filter(profile => profile.name.startsWith("Z"))

  // for-comprehension
  for {
    mark <- SocialNetwork.fetchProfile("fb.id.1-zuck")
    bill <- SocialNetwork.fetchBestFriend(mark)
  } mark.poke(bill)

  Thread.sleep(1000)

  // fallback
  val aProfileNoMatterWhat = SocialNetwork.fetchProfile("unknown id").recover {
    case e: Throwable => Profile("fb.id.0-dummy", "Forever Alone")
  }

  val aFetchedProfileNoMatterWhat = SocialNetwork.fetchProfile("unknow id").recoverWith {
    case e: Throwable => SocialNetwork.fetchProfile("fb.id.0-dummy")
  }

  val fallbackResult = SocialNetwork.fetchProfile("unknow id ").fallbackTo(SocialNetwork.fetchProfile("fb.id.0-dummy"))


  // online banking app
  case class User(name: String)

  case class Transaction(sneder: String, receiver: String, amount: Double, status: String)

  object BankingApp {
    val name = "Rock the JVM banking"

    def fetchUser(name: String): Future[User] = Future {
      // simulate fetching from DB
      Thread.sleep(500)
      User(name)
    }

    def createTransaction(user: User, merchantName: String, amount: Double): Future[Transaction] = Future {
      // simulate some process
      Thread.sleep(500)
      Transaction(user.name, merchantName, amount, "SUCCESS")
    }

    def purchase(userName: String, item: String, merchantName: String, cost: Double): String = {
      // fetch the user from the DB
      // create the transaction
      // WAIT for the transaction fininsh
      val transactionStatusFuture = for {
        user <- fetchUser(userName)
        transaction <- createTransaction(user, merchantName, cost)
      } yield transaction.status

      Await.result(transactionStatusFuture, 2.seconds)
    }
  }

  println(BankingApp.purchase("Daniel", "iphone 12", "rock the jvm store", 3000))

  // promises
  val promise = Promise[Int]
  val future = promise.future
  future.onComplete {
    case Success(r) => println("[consumber] I've received " + r)
  }

  val producer = new Thread(() => {
    println("[producer] crunching number...")
    Thread.sleep(500)
    promise.success(42)
    println("[producer] done.")
  })

  producer.start()
  Thread.sleep(1000)

}
