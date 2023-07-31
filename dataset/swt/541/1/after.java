class PlaceHold {
  public void refresh() {
    checkWidget();
    OS.PtSetResource(webHandle, Pt_ARG_WEB_RELOAD, 1, 0);
  }
}
