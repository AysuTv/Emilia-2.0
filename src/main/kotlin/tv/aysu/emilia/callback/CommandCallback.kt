package tv.aysu.emilia.callback

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.InteractionHook
import net.dv8tion.jda.api.interactions.commands.OptionMapping

class CommandCallback(private val event: SlashCommandInteractionEvent) : InteractionCallBack {

    override val options: List<OptionMapping> = event.options

    override val channel: MessageChannel = event.channel

    override val guild: Guild? = event.guild

    override val guildChannel: GuildChannel?
        get() {

            if(!event.isFromGuild)
                return null

            return event.guildChannel
        }

    override val privateChannel: PrivateChannel?
        get() {

            if(event.isFromGuild)
                return null

            return event.privateChannel
        }

    override val hook: InteractionHook = event.hook

    override val jda: JDA = event.jda

    override val member: Member? = event.member

    override val user: User = event.user

}