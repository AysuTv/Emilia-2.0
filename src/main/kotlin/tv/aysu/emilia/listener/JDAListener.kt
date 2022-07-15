package tv.aysu.emilia.listener

import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import tv.aysu.emilia.Emilia

class JDAListener : ListenerAdapter() {

    override fun onReady(event: ReadyEvent) {
        Emilia.instance.registerCommands()
    }

}