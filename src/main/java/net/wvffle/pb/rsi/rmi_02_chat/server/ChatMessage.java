package net.wvffle.pb.rsi.rmi_02_chat.server;

import java.io.Serializable;
import java.util.Date;

public class ChatMessage implements Serializable {
  private Date date;
  private String user;
  private String content;

  public ChatMessage(Date date, String user, String content) {
    this.date = date;
    this.user = user;
    this.content = content;
  }

  public Date getDate() {
    return date;
  }

  public String getUser() {
    return user;
  }

  public String getContent() {
    return content;
  }
}
