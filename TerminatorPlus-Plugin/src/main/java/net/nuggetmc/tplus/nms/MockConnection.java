package net.nuggetmc.tplus.nms;

import net.minecraft.network.Connection;
import net.minecraft.network.PacketListener;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.net.SocketAddress;

@SuppressWarnings("JavaReflectionMemberAccess")
public class MockConnection extends Connection {
    private static final Field PACKET_LISTENER_FIELD;
    private static final Field DISCONNECT_LISTENER_FIELD;

    static {
        Field packetListenerField = null;
        Field disconnectListenerField = null;

        try {
            // Try known field names from different versions
            packetListenerField = findField(Connection.class, "q", "packetListener");
            disconnectListenerField = findField(Connection.class, "p", "disconnectListener");

            if (packetListenerField != null) packetListenerField.setAccessible(true);
            if (disconnectListenerField != null) disconnectListenerField.setAccessible(true);
        } catch (Exception e) {
            // Fallback: search by type
            for (Field field : Connection.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (PacketListener.class.isAssignableFrom(field.getType())) {
                    if (packetListenerField == null) {
                        packetListenerField = field;
                    } else if (disconnectListenerField == null) {
                        disconnectListenerField = field;
                    }
                }
            }
        }

        PACKET_LISTENER_FIELD = packetListenerField;
        DISCONNECT_LISTENER_FIELD = disconnectListenerField;
    }

    private static Field findField(Class<?> clazz, String... names) {
        for (String name : names) {
            try {
                return clazz.getDeclaredField(name);
            } catch (NoSuchFieldException ignored) {
            }
        }
        return null;
    }

    public MockConnection() {
        super(PacketFlow.SERVERBOUND);
        this.channel = new MockChannel(null);
        this.address = new SocketAddress() {
        };
    }

    @Override
    public void flushChannel() {
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public void send(@NotNull Packet<?> packet) {
    }

    @Override
    public void send(@NotNull Packet<?> packet, PacketSendListener sendListener) {
    }

    @Override
    public void send(@NotNull Packet<?> packet, PacketSendListener sendListener, boolean flag) {
    }

    @Override
    public void setListenerForServerboundHandshake(@NotNull PacketListener packetListener) {
        try {
            if (PACKET_LISTENER_FIELD != null) {
                PACKET_LISTENER_FIELD.set(this, packetListener);
            }
            if (DISCONNECT_LISTENER_FIELD != null) {
                DISCONNECT_LISTENER_FIELD.set(this, null);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
