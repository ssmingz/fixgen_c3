class PlaceHold {
  public void onTestFailure(TestRunEvent evt) {
    getWriter().println("    failure: " + evt.getName());
    getWriter().println(evt.getError().getStackTrace());
  }
}
