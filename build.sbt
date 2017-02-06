// https://mvnrepository.com/artifact/org.scala-lang.modules/scala-xml_2.11
val xml = "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.6"

// https://mvnrepository.com/artifact/com.orientechnologies/orientdb-core
val orientdbCore = "com.orientechnologies" % "orientdb-core" % "2.2.12"

// https://mvnrepository.com/artifact/com.tinkerpop.blueprints/blueprints-core
val bluprintsCore = "com.tinkerpop.blueprints" % "blueprints-core" % "2.6.0"

// https://mvnrepository.com/artifact/com.orientechnologies/orientdb-graphdb
val orientdbGraph = "com.orientechnologies" % "orientdb-graphdb" % "2.2.12"

// https://mvnrepository.com/artifact/com.orientechnologies/orientdb-enterprise
val orientdbEnterprise = "com.orientechnologies" % "orientdb-enterprise" % "2.1.24"

// https://mvnrepository.com/artifact/com.orientechnologies/orientdb-client
val orientdbClient = "com.orientechnologies" % "orientdb-client" % "2.2.12"

// https://mvnrepository.com/artifact/com.orientechnologies/orientdb-tools
val orientdbTools = "com.orientechnologies" % "orientdb-tools" % "2.2.12"

// https://mvnrepository.com/artifact/com.typesafe.play/play-json_2.11
//val jsonFromPLay = "com.typesafe.play" % "play-json_2.11" % "2.5.9"

// https://mvnrepository.com/artifact/org.specs2/specs2-core_2.11
//val specs2_core = "org.specs2" % "specs2-core_2.11" % "3.8.6" % "test"

// https://mvnrepository.com/artifact/junit/junit
//val junit = "junit" % "junit" % "4.12" % "test"

// https://mvnrepository.com/artifact/org.scala-tools/maven-scala-plugin
//val scalaTools = "org.scala-tools" % "maven-scala-plugin" % "2.15.2"

// https://mvnrepository.com/artifact/org.specs2/specs2-junit_2.11
//val specs2JUnit = "org.specs2" % "specs2-junit_2.11" % "3.8.6"

// https://mvnrepository.com/artifact/org.specs2/specs2_2.10
//val specs2 = "org.specs2" % "specs2_2.11" % "3.3"

name := """admin_web_play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
  ,orientdbCore
  ,bluprintsCore
  ,orientdbGraph
  ,orientdbEnterprise
  ,orientdbClient
  ,orientdbTools
)

