class PlaceHold {
  public void messageLogged(BuildEvent event) {
    if (event.getPriority() <= msgOutputLevel) {
      if (event.getTask() != null) {
        String name = event.getTask().getClass().getName();
        int pos = name.lastIndexOf(".");
        if (pos != (-1)) {
          name = name.substring(pos + 1);
        }
        String msg = ("[" + name) + "] ";
        for (int i = 0; i < (LEFT_COLUMN_SIZE - msg.length()); i++) {
          out.print(" ");
        }
        out.print(msg);
      }
      out.println(event.getMessage());
    }
  }
}
