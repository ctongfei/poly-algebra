name := "poly-algebra"

version := "0.2.9-SNAPSHOT"

organization := "me.tongfei"

scalaVersion := "2.11.7"

isSnapshot := true

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "org.scala-lang"    %  "scala-reflect"   % scalaVersion.value

libraryDependencies += "me.tongfei"        %% "poly-macroutil"  % "0.1.0-SNAPSHOT"

libraryDependencies += "org.scalatest"     %% "scalatest"       % "2.2.2"             % Test

libraryDependencies += "org.scalacheck"    %% "scalacheck"      % "1.12.4"            % Test

libraryDependencies += "com.storm-enroute" %% "scalameter-core" % "0.7"               % Test

scalacOptions in (Compile, doc) += "-diagrams"

classDiagramSettings

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomExtra :=
  <url>http://github.com/ctongfei/poly-algebra</url>
    <licenses>
      <license>
        <name>MIT</name>
        <url>http://opensource.org/licenses/MIT</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:ctongfei/poly-algebra.git</url>
      <connection>scm:git:git@github.com:ctongfei/poly-algebra.git</connection>
    </scm>
    <developers>
      <developer>
        <id>ctongfei</id>
        <name>Tongfei Chen</name>
        <url>http://tongfei.me/</url>
      </developer>
    </developers>

