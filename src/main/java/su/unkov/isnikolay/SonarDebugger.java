package su.unkov.isnikolay;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;
import xyz.jonesdev.sonar.api.Sonar;

@Plugin(id = "sonardebugger", name = "SonarDebugger", version = BuildConstants.VERSION, description = "Debbuger for detect events for Sonar plugin", url = "isnikolay.unkov.su", authors = {"isNIKOLAY"})
public class SonarDebugger {

    @Inject
    private ProxyServer server;
    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("Sonar Debbuger plugin by isNIKOLAY initialized");
        Sonar.get().getEventManager().registerListener(new SonarEventsHandler());
        logger.info("Registered Sonar events for debug");
    }
}
