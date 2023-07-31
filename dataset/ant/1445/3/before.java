class PlaceHold {
  public void buildFinished(BuildEvent event) {
    log("< BUILD FINISHED", MSG_DEBUG);
    Throwable error = event.getException();
    if (error == null) {
      out.println(LINE_SEP + "BUILD SUCCESSFUL");
    } else {
      out.println((LINE_SEP + "BUILD FAILED") + LINE_SEP);
      error.printStackTrace(out);
    }
    out.flush();
    out.close();
  }
}
