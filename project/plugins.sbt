resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/maven-releases/"

resolvers += Classpaths.typesafeReleases

resolvers += Resolver.sonatypeRepo("releases")

addSbtPlugin("io.spray" %% "sbt-revolver" % "0.9.1")
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.3.4")