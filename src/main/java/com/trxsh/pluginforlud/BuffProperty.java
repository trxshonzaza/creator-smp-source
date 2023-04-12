package com.trxsh.pluginforlud;

import java.util.UUID;

public class BuffProperty {

    public Buff b;
    public UUID id;

    public BuffProperty(Buff buff, UUID owner) {

        this.b = buff;
        this.id = owner;

    }

    public Buff getBuff() {return b;}
    public UUID getID() {return id;}

}
