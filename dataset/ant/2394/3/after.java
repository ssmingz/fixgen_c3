class PlaceHold {
  public void onTestError(TestRunEvent evt) {
    getWriter().println("    error: " + evt.getName());
    getWriter().println(evt.getError().getStackTrace());
  }
}
