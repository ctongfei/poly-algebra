import scala.io._

name := "poly-algebra"
version := "0.4.0-SNAPSHOT"
organization := "me.tongfei"
scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.11.8", "2.12.1")
isSnapshot := true

libraryDependencies += "org.scala-lang"    %  "scala-reflect"   % scalaVersion.value
libraryDependencies += "me.tongfei"        %% "poly-macroutil"  % "0.2.0"

libraryDependencies += "org.scalatest"     %% "scalatest"       % "3.0.0"             % Test
libraryDependencies += "org.scalacheck"    %% "scalacheck"      % "1.13.4"            % Test
libraryDependencies += "com.storm-enroute" %% "scalameter-core" % "0.8.2"             % Test

scalacOptions in (Compile, doc) ++= Seq("-diagrams")

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

// credit to http://stackoverflow.com/a/32114551/2990673
lazy val mathFormulaInDoc = taskKey[Unit]("add MathJax script import in scaladoc html to display LaTeX formula")

mathFormulaInDoc := {
  val apiDir = (doc in Compile).value
  val docDir = apiDir    // /"some"/"subfolder"  // in my case, only api/some/solder is parsed
  // will replace this "importTag" by "scriptLine
  // find all html file and apply patch
  if(docDir.isDirectory)
    listHtmlFile(docDir).foreach { f =>
      val content = Source.fromFile(f).getLines().mkString("\n")
      val writer = new java.io.PrintWriter(f)
      writer.write(content.replace("<head>", """<head><script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"> </script>"""))
      writer.close()
    }
}

// attach this task to doc task
mathFormulaInDoc <<= mathFormulaInDoc triggeredBy (doc in Compile)

// function that find html files recursively
def listHtmlFile(dir: java.io.File): List[java.io.File] = {
  dir.listFiles.toList.flatMap { f =>
    if(f.getName.endsWith(".html")) List(f)
    else if(f.isDirectory)          listHtmlFile(f)
    else                            List[File]()
  }
}
