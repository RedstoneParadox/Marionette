# Marionette
[ ![Download](https://api.bintray.com/packages/redstoneparadox/mods/Marionette/images/download.svg?version=0.3.0-beta) ](https://bintray.com/redstoneparadox/mods/Marionette/0.3.0-beta/link)

Marionette is a Minecraft modding library for Fabric that aims to make animating custom entities and block entities more approachable.

Discord: https://discord.gg/xSZHvCc

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
