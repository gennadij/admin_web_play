val orientdbEnterprise = "com.orientechnologies" % "orientdb-enterprise" % "2.1.+"
val orientdbGraph = "com.orientechnologies" % "orientdb-graphdb" % "2.2.+"
val orientdbCore = "com.orientechnologies" % "orientdb-core" % "2.2.3"
val orientdbClient = "com.orientechnologies" % "orientdb-client" % "2.2.+"
val bluprintsCore = "com.tinkerpop.blueprints" % "blueprints-core" % "2.6.+"

name := """admin"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  orientdbCore,
  bluprintsCore,
  orientdbGraph,
  orientdbClient,
  orientdbEnterprise

)

