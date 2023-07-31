class PlaceHold {
  public void messageLogged(BuildEvent event) {
    if (event.getPriority() <= getBuildInfo().getOutputMessageLevel()) {
      String msg = "";
      if (event.getTask() != null) {
        msg = ("[" + event.getTask().getName()) + "] ";
      }
      getMessageTextArea().append((lineSeparator + msg) + event.getMessage());
    }
  }
}
