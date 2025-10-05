package su.unkov.isnikolay;

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

            System.out.println("[J] User joined to verify process: " + joinEvent.getUser().getUsername() + " (IP: " + joinEvent.getUser().getInetAddress() + ")" + " | PV: " + joinEvent.getUser().getProtocolVersion());
        }
        else if (event instanceof UserVerifySuccessEvent) {
            final UserVerifySuccessEvent successEvent = (UserVerifySuccessEvent) event;

            System.out.printf("[+] User successfully verified: %s (IP: %s) (took %s ms to verify)%n",
                    successEvent.getUser().getUsername(), successEvent.getUser().getInetAddress(), successEvent.getUser().getLoginTimer());
        }
        else if (event instanceof UserVerifyFailedEvent) {
            final UserVerifyFailedEvent failedEvent = (UserVerifyFailedEvent) event;

            System.out.println("[-] User failed verify process: " + failedEvent.getUser().getUsername() + " (IP: " + failedEvent.getUser().getInetAddress() + ")" + " | PV: " + failedEvent.getUser().getProtocolVersion());
            System.out.println("REASON: " + failedEvent.getReason());
        }
        else if (event instanceof UserBlacklistedEvent) {
            final UserBlacklistedEvent blacklistedEvent = (UserBlacklistedEvent) event;

            System.out.println("[BLACKLIST] User was blocked: " + blacklistedEvent.getUser().getUsername() + " (IP: " + blacklistedEvent.getUser().getInetAddress() + ")" + " | PV: " + blacklistedEvent.getUser().getProtocolVersion());
        }


    }
}
