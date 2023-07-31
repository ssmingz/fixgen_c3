class PlaceHold {
  public void onTestError(TestRunEvent evt) {
    String msg = RES.getString("brief.status-error.msg", evt.getName(), evt.getStackTrace());
    getWriter().println(msg);
    super.onTestError(evt);
  }
}
