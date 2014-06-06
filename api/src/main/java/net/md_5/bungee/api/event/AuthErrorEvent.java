package net.md_5.bungee.api.event;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.md_5.bungee.api.Callback;

/**
 * Event called when authenticating a user with Mojang auth servers fails
 * because of a service interruption.
 */
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class AuthErrorEvent extends AsyncEvent<AuthErrorEvent>
{
    /**
     * UUID to use for this player. Set by event handler through the
     * {@link #allow(UUID)} method to allow the player to connect even though
     * they could not be authenticated with Mojang.
     */
    @Getter
    private UUID uuid;

    /**
     * Username of the connecting player.
     */
    @Getter
    private final String username;

    /**
     * HTTP error received when trying to check authentication.
     */
    @Getter
    private final Throwable error;

    public AuthErrorEvent(String username, Throwable error, Callback<AuthErrorEvent> done)
    {
        super( done );
        this.username = username;
        this.error = error;
    }

    /**
     * Indicates whether or not the player will be allowed to connect even with
     * the authentication error. UUID will be set if this returns true.
     */
    public boolean isAllowed()
    {
        return uuid != null;
    }

    /**
     * Allows the player to continue connecting instead of getting kicked for
     * an authentication error.
     * @param uuid UUID to use for the player
     */
    public void allow(UUID uuid)
    {
        this.uuid = uuid;
    }
}
