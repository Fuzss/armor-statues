plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-neoforge")
}

dependencies {
    modCompileOnly(sharedLibs.puzzleslib.common)
    modApi(sharedLibs.puzzleslib.neoforge)
    modCompileOnly(sharedLibs.statuemenus.common)
    modApi(sharedLibs.statuemenus.neoforge)
    include(sharedLibs.statuemenus.neoforge)
}
