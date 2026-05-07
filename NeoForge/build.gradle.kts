plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-neoforge")
}

dependencies {
    modApi(sharedLibs.puzzleslib.neoforge)
    modApi(sharedLibs.statuemenus.neoforge)
    include(sharedLibs.statuemenus.neoforge)
}
