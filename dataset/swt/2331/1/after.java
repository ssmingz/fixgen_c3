class PlaceHold {
  public int getBorderWidth() {
    checkWidget();
    long borderHandle = borderHandle();
    int bits1 = OS.GetWindowLong(borderHandle, GWL_EXSTYLE);
    if ((bits1 & OS.WS_EX_CLIENTEDGE) != 0) {
      return OS.GetSystemMetrics(SM_CXEDGE);
    }
    if ((bits1 & OS.WS_EX_STATICEDGE) != 0) {
      return OS.GetSystemMetrics(SM_CXBORDER);
    }
    int bits2 = OS.GetWindowLong(borderHandle, GWL_STYLE);
    if ((bits2 & OS.WS_BORDER) != 0) {
      return OS.GetSystemMetrics(SM_CXBORDER);
    }
    return 0;
  }
}
