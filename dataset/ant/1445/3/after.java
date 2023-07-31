class PlaceHold {
  public void buildFinished(BuildEvent event) {
    getLogger().debug("< BUILD FINISHED");
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
