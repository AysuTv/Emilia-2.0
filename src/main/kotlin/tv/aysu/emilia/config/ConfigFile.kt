package tv.aysu.emilia.config

import me.daarkii.bungee.core.config.Config
import tv.aysu.emilia.Emilia
import java.io.File

class ConfigFile(folder: File) : Config(File(folder, "config.yml"), "config.yml") {

    override fun afterLoad() {
        Emilia.instance.startBot(this.getString("token"))
    }

}