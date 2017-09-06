import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.prj",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    EclipseKeys.withSource := true,
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "com.intuit.quickbooks-online" % "ipp-v3-java-data" % "3.0.0",
    libraryDependencies += "com.intuit.quickbooks-online" % "ipp-v3-java-devkit" % "3.0.0",
    libraryDependencies += "com.intuit.quickbooks-online" % "oauth2-platform-api" % "3.0.0",
    libraryDependencies += "com.nrinaudo" %% "kantan.csv-scalaz" % "0.2.1",
    libraryDependencies += "com.nrinaudo" %% "kantan.csv-java8" % "0.2.1",
    libraryDependencies += "com.nrinaudo" %% "kantan.csv-generic" % "0.2.1",
    libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.5"
  )
