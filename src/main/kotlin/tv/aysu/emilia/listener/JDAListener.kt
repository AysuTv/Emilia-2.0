package tv.aysu.emilia.listener

import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import tv.aysu.emilia.Emilia

class JDAListener(private val emilia: Emilia) : ListenerAdapter() {

    override fun onReady(event: ReadyEvent) {
        emilia.commandHandler.acceptCommands()
    }

}