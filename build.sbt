name := """socialCamp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test,
  "com.wordnik" %% "swagger-play2" % "1.3.12",
  "com.wix" %% "accord-core" % "0.4",
  "com.couchbase.client" % "java-client" % "2.2.5",
  "io.reactivex" % "rxscala_2.11" % "0.26.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"