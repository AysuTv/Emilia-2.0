package tv.aysu.emilia.handler

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import tv.aysu.emilia.Emilia
import tv.aysu.emilia.command.Command
import tv.aysu.emilia.callback.CommandCallback
import java.util.LinkedList
import java.util.concurrent.CompletableFuture

class CommandHandler(private val emilia: Emilia) : ListenerAdapter() {

    val commands: MutableSet<Command> = LinkedHashSet()

    fun registerCommand(command: Command) {
        this.commands.add(command)
    }

    fun acceptCommands() {

        val jda = emilia.jda
        val commandData: MutableList<CommandData> = LinkedList()

        //load commandData
        commands.forEach { command -> commandData.add(command.options) }

        //update commands on every guild
        jda.guilds.forEach { guild -> guild.updateCommands().addCommands(commandData).queue() }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        //execute all checks and command async
        CompletableFuture.runAsync {

            //send bot is thinking message
            event.deferReply(true)

            //create callback
            val callback = CommandCallback(event)

            commands.forEach { command ->

                if(command.onlyGuild && !event.isFromGuild)
                    return@runAsync

                if(command.name != event.name)
                    return@runAsync

                command.execute(callback)
            }
        }
    }

}