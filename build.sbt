name := """socialCamp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test,
  "org.reactivecouchbase" %% "reactivecouchbase-play" % "0.3",
  "com.wordnik" %% "swagger-play2" % "1.3.12",
  "com.wix" %% "accord-core" % "0.4"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "ReactiveCouchbase" at "https://raw.github.com/ReactiveCouchbase/repository/master/releases"