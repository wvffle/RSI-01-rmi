package net.wvffle.pb.rsi.rmi_02_chat.server;

import java.io.Serializable;
import java.util.Date;

public class ChatUser implements Serializable {
  private Date hb;
  private String name;
  public ChatUser(String name) {
    this.name = name;
    hb();
  }

  public Date getHb() {
    return hb;
  }

  public String getName() {
    return name;
  }

  public void hb () {
    this.hb = new Date();
  }
}
