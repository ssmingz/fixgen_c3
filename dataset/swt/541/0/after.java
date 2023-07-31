class PlaceHold {
  public void stop() {
    checkWidget();
    OS.PtSetResource(webHandle, Pt_ARG_WEB_STOP, 1, 0);
  }
}
