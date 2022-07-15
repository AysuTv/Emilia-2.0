package tv.aysu.emilia.command

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import tv.aysu.emilia.callback.InteractionCallBack

interface Command {

    /**
     * Executes a command
     *
     * @param callback the data which we get after a command execution
     */
    fun execute(callback: InteractionCallBack)

    /**
     * The name of the command
     */
    val name: String

    /**
     * Should be false if the command should be only available on a guild
     */
    val onlyGuild: Boolean

    /**
     * Options for the command with arguments, sub-commands etc.
     */
    val options: CommandData

    /**
     * A list with all roles which can interact with the command
     */
    val groups: List<String>

    /**
     * Checks if the given member has the permission to execute the command
     *
     * @param member the member to check the permissions
     * @param roles the roles which can interact with the command
     * @return true if the member can use the command
     */
    fun checkPermissions(member: Member, roles: List<String>) : Boolean {
        for(groupID in roles) {
            if(member.roles.stream().anyMatch { it.id == groupID })
                return true
        }
        return false
    }

}