class PlaceHold {
  public void messageLogged(BuildEvent event) {
    if (event.getPriority() <= msgOutputLevel) {
      StringBuffer message = new StringBuffer();
      if (event.getTask() != null) {
        String name = event.getTask().getTaskName();
        if (!emacsMode) {
          String label = ("[" + name) + "] ";
          for (int i = 0; i < (LEFT_COLUMN_SIZE - label.length()); i++) {
            message.append(" ");
          }
          message.append(label);
        }
      }
      message.append(event.getMessage());
      String msg = message.toString();
      if (event.getPriority() != Project.MSG_ERR) {
        out.println(msg);
      } else {
        err.println(msg);
      }
      log(msg);
    }
  }
}
