name := """applied-functional-scala"""

val monocleVersion = "1.2.2"
val ioIterateeVersion = "0.6.0-M1"
val fs2Version = "0.9.0-M6"

val commonSettings = Seq(
  scalaVersion := "2.11.8",
  organization := "com.appliedscala",
  version := "1.0-SNAPSHOT",
  resolvers += Resolver.sonatypeRepo("snapshots"),
  resolvers += Resolver.sonatypeRepo("releases"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
)

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test"
)

val catsDependencies = Seq(
  "org.typelevel" %% "cats" % "0.6.1",
  "org.typelevel" %% "cats-free" % "0.6.1"
)

val iterateeDependencies = Seq(
  "io.iteratee" %% "iteratee-core" % ioIterateeVersion,
  "io.iteratee" %% "iteratee-scalaz" % ioIterateeVersion,
  "io.iteratee" %% "iteratee-files" % ioIterateeVersion
)

val fs2Dependencies = Seq(
  "co.fs2" %% "fs2-core" % fs2Version,
  "co.fs2" %% "fs2-io" % fs2Version
)

val scalazDependencies = Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.4",
  "org.scalaz" %% "scalaz-concurrent" % "7.2.4",
  "org.asynchttpclient" % "async-http-client" % "2.0.11",
  "com.typesafe.akka" %% "akka-actor" % "2.4.8"
)

val typeclassesDependencies = Seq(
  "com.github.mpilquist" %% "simulacrum" % "0.7.0"
)

val monocleDependencies = Seq(
  "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
  "org.spire-math" %% "spire" % "0.11.0"
)

lazy val root = (project in file(".")).settings(commonSettings).aggregate(typeclasses, cats)

lazy val typeclasses = (project in file("typeclasses")).settings(commonSettings).settings(libraryDependencies ++= typeclassesDependencies)

lazy val cats = (project in file("cats")).settings(commonSettings).settings(libraryDependencies ++= (catsDependencies ++ scalazDependencies))

lazy val iteratees = (project in file("iteratees")).settings(commonSettings).settings(libraryDependencies ++= iterateeDependencies)

lazy val monocle = (project in file("monocle")).settings(commonSettings).settings(libraryDependencies ++= monocleDependencies)

lazy val fs2 = (project in file("fs2")).settings(commonSettings).settings(libraryDependencies ++= fs2Dependencies)

lazy val scalaz = (project in file("scalaz")).settings(commonSettings).settings(libraryDependencies ++= scalazDependencies)