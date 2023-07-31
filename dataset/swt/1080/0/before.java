class PlaceHold {
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    int border = 0;
    byte[] hasBorder = new byte[1];
    OS.GetControlData(
        handle,
        ((short) (kControlEntireControl)),
        kControlDataBrowserIncludesFrameAndFocusTag,
        1,
        hasBorder,
        null);
    if (hasBorder[0] != 0) {
      int[] outMetric = new int[1];
      OS.GetThemeMetric(kThemeMetricFocusRectOutset, outMetric);
      border += outMetric[0];
    }
    Rect rect = new Rect();
    OS.GetDataBrowserScrollBarInset(handle, rect);
    x -= rect.left + border;
    y -= rect.top + border;
    width += ((rect.left + rect.right) + border) + border;
    height += ((rect.top + rect.bottom) + border) + border;
    return new Rectangle(x, y, width, height);
  }
}
