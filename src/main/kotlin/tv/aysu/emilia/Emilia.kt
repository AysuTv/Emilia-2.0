package tv.aysu.emilia

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import tv.aysu.emilia.config.Config
import tv.aysu.emilia.config.DefaultConfig
import tv.aysu.emilia.handler.CommandHandler
import tv.aysu.emilia.listener.JDAListener
import java.io.File

class Emilia(val dataFolder: File) {

    init {
        instance = this
    }

    lateinit var jda: JDA

    lateinit var config: Config
    lateinit var commandHandler: CommandHandler

    fun start() {

        this.commandHandler = CommandHandler(this)
        this.config = DefaultConfig(File(dataFolder, "config.yml"), "config.yml")

        this.startBot(this.config.getString("token"))
    }

    private fun startBot(token: String) {

        val builder = JDABuilder.create(token, GatewayIntent.values().asList())
            .setMemberCachePolicy(MemberCachePolicy.DEFAULT)

        //load commands
        this.registerCommands()

        //register listener
        builder.addEventListeners(JDAListener(this))

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