package com.sl.coljourney.scala.sealedr

/**
  * sealed关键字：可用于修饰trait和class。
  * 关键字作用：
  * *  1.如果子类都明确的情况下，为了防止继承滥用，只能在sealed关键字声明的class或trait同文件中声明子类，否则报错。
  * *    错误信息：illegal inheritance from sealed trait Animal
  * *  2.在使用sealed修饰的子类进行模式匹配时，如果模式匹配的条件未包含所有的子类，则在编译时候，scala编译器会给出警告。
  * *    警告信息：* Warning:(18, 38) match may not be exhaustive.
  * *                               It would fail on the following input: Elephant()
  * *                               def animal(animal: Animal): Unit = animal match {
  * *    如果使用了 case _ => 则不会进行警告。
  *
  * 在Spark源码中，DAGSchedulerEvent便使用了sealed关键字，表示不同的事件类型
  * 在DAGSchedulerEvent中的doOnReceive(event: DAGSchedulerEvent)方法进行了模式匹配，根据不同事件执行不同操作。
  *
  * Created by L on 2018/5/4.
  */
object SealedTest {

  def main(args: Array[String]): Unit = {

    animal(Dog())
    animal(Dog())

  }

  /**
    * Warning:(18, 38) match may not be exhaustive.
    * It would fail on the following input: Elephant()
    * def animal(animal: Animal): Unit = animal match {
    *
    * @param animal Animal
    */
  def animal(animal: Animal): Unit = animal match {
    case Dog() => println("dog")
    case Bird() => println("bird")
    case Cat() => println("cat")
    case _ => println("wrong")
  }

}

/**
  * illegal inheritance from sealed trait Animal
  */
// case class Monkey() extends Animal