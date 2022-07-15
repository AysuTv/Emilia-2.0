package tv.aysu.emilia.command.impl

import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import tv.aysu.emilia.callback.InteractionCallBack
import tv.aysu.emilia.command.Command
import tv.aysu.emilia.utils.Groups

class WarnCMD : Command {

    /**
     * Executes a command
     *
     * @param callback the data which we get after a command execution
     */
    override fun execute(callback: InteractionCallBack) {

        val user = callback.user
        val member = callback.member!!

        if(!checkPermissions(member, this.groups))
            return

        val hook = callback.hook
        val target = callback.getOption("user")?.asUser
        val reason = callback.getOption("reason")?.asString

        if(target == null || reason == null) {
            hook.sendMessage("The reason or the user should not be null.").setEphemeral(true).queue()
            return
        }

        if(!this.checkPermissions(member, bypassGroups) && !this.isReason(reason)) {

            val builder = StringBuilder().append("You can only use this reasons:")

            for(reasonName in reasons) {
                if(reasonName == reasons.last())
                    builder.append("» **$reasonName**")
                else
                    builder.append("» **$reasonName** \n")
            }

            hook.sendMessage(builder.toString()).setEphemeral(true).queue()
            return
        }

        if(target == user) {
            hook.sendMessage("You can not warn yourself").setEphemeral(true).queue()
            return
        }


    }

    override val name: String = "warn"

    override val options: CommandData
        get() {
            return Commands.slash("warn", "warns a member with the given reason")
                .addOption(OptionType.USER, "user", "the user who should be warned")
                .addOption(OptionType.STRING, "reason", "the reason for the warn")
                .setGuildOnly(onlyGuild)
        }

    override val onlyGuild: Boolean = true

    override val groups: List<String> = Groups.supTeam

    private val bypassGroups = Groups.srTeam

    private val reasons: List<String> = mutableListOf("Beleidigung", "Spam", "Werbung", "Bugusing", "Regelverstoß")

    private fun isReason(name: String) : Boolean {
        return this.reasons.stream().anyMatch { it.lowercase() == name.lowercase() }
    }
}