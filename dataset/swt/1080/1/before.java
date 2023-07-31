class PlaceHold {
  public void setTopItem(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    showItem(item, false);
    int columnId = (columnCount == 0) ? column_id : columns[0].id;
    OS.RevealDataBrowserItem(
        handle, item.id, columnId, ((byte) (kDataBrowserRevealWithoutSelecting)));
    Rect rect = new Rect();
    if (OS.GetDataBrowserItemPartBounds(
            handle, item.id, column_id, kDataBrowserPropertyEnclosingPart, rect)
        == OS.noErr) {
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
      int[] top = new int[1];
      int[] left = new int[1];
      OS.GetDataBrowserScrollPosition(handle, top, left);
      OS.SetDataBrowserScrollPosition(handle, (top[0] + rect.top) - border, left[0]);
    }
  }
}
