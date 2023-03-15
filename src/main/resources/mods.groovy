ModsDotGroovy.make {
    modLoader = 'javafml'
    loaderVersion = '[40,)'

    license = 'MIT'
    // A URL to refer people to when problems occur with this mod
    issueTrackerUrl = 'https://github.com/Matyrobbrt/PowerfulTotems/issues'

    mod {
        modId = 'powerfultotems'
        displayName = 'Powerful Totems'
        displayUrl = 'https://www.curseforge.com/minecraft/mc-mods/powerful-totems'

        version = this.version

        description = '''A mod adding a lot of powerful totems to the game.'''
        authors = ['Matyrobbrt']

        logoFile = 'powerfultotems.png'

        dependencies {
            forge = "[${this.forgeVersion},)"
            minecraft = this.minecraftVersionRange
        }
    }
}