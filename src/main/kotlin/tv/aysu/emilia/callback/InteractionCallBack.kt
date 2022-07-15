package tv.aysu.emilia.callback

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.GuildChannel
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.PrivateChannel
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.interactions.InteractionHook
import net.dv8tion.jda.api.interactions.commands.OptionMapping
import java.util.stream.Collectors

interface InteractionCallBack {

    val guild: Guild?

    val jda: JDA

    val channel: MessageChannel

    val guildChannel: GuildChannel?

    val privateChannel: PrivateChannel?

    val hook: InteractionHook

    val options: List<OptionMapping>

    val user: User

    val member: Member?

    fun getOption(name: String) : OptionMapping? {
        return options.stream().filter { it.name == name }.collect(Collectors.toList()).firstOrNull()
    }

}