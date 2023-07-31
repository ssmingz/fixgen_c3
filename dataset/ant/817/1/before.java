class PlaceHold {
  public void messageLogged(BuildEvent event) {
    int priority = event.getPriority();
    if (priority <= msgOutputLevel) {
      StringBuffer message = new StringBuffer();
      if ((event.getTask() != null) && (!emacsMode)) {
        String name = event.getTask().getTaskName();
        String label = ("[" + name) + "] ";
        int size = LEFT_COLUMN_SIZE - label.length();
        StringBuffer tmp = new StringBuffer(size);
        for (int i = 0; i < size; i++) {
          tmp.append(" ");
        }
        tmp.append(label);
        label = tmp.toString();
        StringTokenizer tok = new StringTokenizer(event.getMessage(), "\r\n", false);
        boolean first = true;
        while (tok.hasMoreTokens()) {
          if (!first) {
            message.append(LINE_SEP);
          }
          first = false;
          message.append(label);
          message.append(tok.nextToken());
        }
      } else {
        message.append(event.getMessage());
      }
      String msg = message.toString();
      if (priority != Project.MSG_ERR) {
        printMessage(msg, out, priority);
      } else {
        printMessage(msg, err, priority);
      }
      log(msg);
    }
  }
}
