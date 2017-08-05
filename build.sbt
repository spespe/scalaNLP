name := "scalaPNLP"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies  ++= Seq(
  // Last stable release
  "org.scalanlp" %% "nak" % "1.3",
  "org.scalanlp" %% "breeze" % "0.12",
  "org.scalanlp" %% "breeze-natives" % "0.12",
  "org.scalanlp" %% "breeze-viz" % "0.12",
  "org.scalanlp" %% "epic-ner-en-conll" % "2015.1.25",
  "org.scalanlp" %% "epic-pos-en" % "2015.1.25",
  "org.scalanlp" %% "epic-parser-en-span" % "2015.1.25",
  "org.scalanlp" %% "epic-ner-en-conll" % "2015.1.25",
  "org.apache.opennlp" % "opennlp-tools" % "1.6.0",
  "de.julielab" % "aliasi-lingpipe" % "4.1.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.6.0"
)

resolvers ++= Seq(
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

publishMavenStyle := true


publishArtifact in Test := false


pomIncludeRepository := { _ => false }


pomExtra := (
  <url>http://scalanlp.org/</url>
    <licenses>
      <license>
        <name>Apache License 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:scalanlp/chalk.git</url>
      <connection>scm:git:git@github.com:scalanlp/chalk.git</connection>
    </scm>
    <developers>
      <developer>
        <id>jasonbaldridge</id>
        <name>Jason Baldridge</name>
        <url>http://www.jasonbaldridge.com</url>
      </developer>
    </developers>
  )
