name := "TravelBuddy"
 
version := "1.0" 
      
lazy val `travelbuddy` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )
libraryDependencies += jdbc
libraryDependencies += evolutions
libraryDependencies += cache
libraryDependencies += cacheApi

libraryDependencies += "com.h2database" % "h2" % "1.4.197"

libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.2"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )