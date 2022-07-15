package tv.aysu.emilia

import me.daarkii.bungee.core.addon.Addon
import me.daarkii.bungee.core.addon.AddonInfo
import me.daarkii.bungee.core.config.Config
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import tv.aysu.emilia.config.ConfigFile
import tv.aysu.emilia.handler.CommandHandler
import tv.aysu.emilia.listener.JDAListener

class Emilia(info: AddonInfo) : Addon(info) {

    lateinit var jda: JDA
    lateinit var config: Config
    lateinit var commandHandler: CommandHandler

    override fun onStart() {
        instance = this

        this.commandHandler = CommandHandler(this)
        this.config = ConfigFile(this.dataFolder)
    }

    fun startBot(token: String) {

        val builder = JDABuilder.create(token, GatewayIntent.values().asList())
            .setMemberCachePolicy(MemberCachePolicy.DEFAULT)

        //register listener
        builder.addEventListeners(JDAListener())

        this.jda = builder.build()
    }

    /**
     * Registers every command on the commandHandler
     * The Commands will be registered on guilds when jda is ready
     */
    fun registerCommands() {


    }

    companion object {
        @JvmStatic
        lateinit var instance: Emilia
            private set
    }
}