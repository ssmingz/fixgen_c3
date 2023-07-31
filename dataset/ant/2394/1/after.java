class PlaceHold {
  public void onTestFailure(TestRunEvent evt) {
    String msg =
        RES.getString("brief.status-failure.msg", evt.getName(), evt.getError().getStackTrace());
    getWriter().println(msg);
    super.onTestFailure(evt);
  }
}
