# Marionette
[ ![Download](https://api.bintray.com/packages/redstoneparadox/mods/Marionette/images/download.svg?version=0.1.0-alpha) ](https://bintray.com/redstoneparadox/mods/Marionette/0.1.0-alpha/link)

Marionette is a library that aims to make creating and animating custom entity models more approachable.

## Including Marionette in your Project

`build.gradle`:
```gradle
repositories {
    maven {
        url = "https://dl.bintray.com/redstoneparadox/mods"
    }
}

dependencies {
    modApi("io.github.redstoneparadox:Marionette:${project.marionette_version}") {
        exclude group: 'net.fabricmc.fabric-api'
    }
    include "io.github.redstoneparadox:Marionette:${project.marionette_version}"
}
```
