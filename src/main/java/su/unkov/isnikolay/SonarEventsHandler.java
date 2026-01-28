package su.unkov.isnikolay;

import xyz.jonesdev.sonar.api.Sonar;
import xyz.jonesdev.sonar.api.event.SonarEvent;
import xyz.jonesdev.sonar.api.event.SonarEventListener;
import xyz.jonesdev.sonar.api.event.impl.UserBlacklistedEvent;
import xyz.jonesdev.sonar.api.event.impl.UserVerifyFailedEvent;
import xyz.jonesdev.sonar.api.event.impl.UserVerifyJoinEvent;
import xyz.jonesdev.sonar.api.event.impl.UserVerifySuccessEvent;

public final class SonarEventsHandler implements SonarEventListener {

    @Override
    public void handle(final SonarEvent event) {
        if (event instanceof UserVerifyJoinEvent)
        {
            final UserVerifyJoinEvent joinEvent = (UserVerifyJoinEvent) event;

            Sonar.get().getLogger().info("[DEBUGGER | J] " + joinEvent.getUser().getUsername() + " (IP: " + joinEvent.getUser().getInetAddress() + ")" + " | PV: " + joinEvent.getUser().getProtocolVersion());
        }
        else if (event instanceof UserVerifySuccessEvent) {
            final UserVerifySuccessEvent successEvent = (UserVerifySuccessEvent) event;

            Sonar.get().getLogger().info("[DEBUGGER | V] " + successEvent.getUser().getUsername() + " (IP: " + successEvent.getUser().getInetAddress() + ") (took " + successEvent.getUser().getLoginTimer() + " ms to verify)",
                    successEvent.getUser().getUsername(), successEvent.getUser().getInetAddress(), successEvent.getUser().getLoginTimer());
        }
        else if (event instanceof UserVerifyFailedEvent) {
            final UserVerifyFailedEvent failedEvent = (UserVerifyFailedEvent) event;

            Sonar.get().getLogger().info("[DEBUGGER | F] " + failedEvent.getUser().getUsername() + " (IP: " + failedEvent.getUser().getInetAddress() + ")" + " | PV: " + failedEvent.getUser().getProtocolVersion());
            Sonar.get().getLogger().info("REASON: " + failedEvent.getReason());
        }
        else if (event instanceof UserBlacklistedEvent) {
            final UserBlacklistedEvent blacklistedEvent = (UserBlacklistedEvent) event;

            Sonar.get().getLogger().info("[DEBUGGER | B] " + blacklistedEvent.getUser().getUsername() + " (IP: " + blacklistedEvent.getUser().getInetAddress() + ")" + " | PV: " + blacklistedEvent.getUser().getProtocolVersion());
        }


    }
}
