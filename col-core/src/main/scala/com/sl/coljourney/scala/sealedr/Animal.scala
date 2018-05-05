package com.sl.coljourney.scala.sealedr

/**
  * Created by L on 2018/5/4.
  */
sealed trait Animal

case class Dog() extends Animal

case class Bird() extends Animal

case class Cat() extends Animal

case class Elephant() extends Animal
