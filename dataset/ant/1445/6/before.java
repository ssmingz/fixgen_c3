class PlaceHold {
  public void messageLogged(BuildEvent event) {
    log("--- MESSAGE LOGGED", MSG_DEBUG);
    StringBuffer buf = new StringBuffer();
    if (event.getTask() != null) {
      String name = ("[" + event.getTask().getName()) + "]";
      for (int i = 0; i < (12 - name.length()); i++) {
        buf.append(" ");
      }
      buf.append(name);
    }
    buf.append(event.getMessage());
    log(buf.toString(), event.getPriority());
  }
}
