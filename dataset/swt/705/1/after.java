class PlaceHold {
  String _getToolTip(int x, int y) {
    if (showMin && minRect.contains(x, y)) {
      return minimized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Minimize");
    }
    if (showMax && maxRect.contains(x, y)) {
      return maximized ? SWT.getMessage("SWT_Restore") : SWT.getMessage("SWT_Maximize");
    }
    if (showChevron && chevronRect.contains(x, y)) {
      return SWT.getMessage("SWT_ShowList");
    }
    CTabItem item = getItem(new Point(x, y));
    if (item == null) {
      return null;
    }
    if (!item.showing) {
      return null;
    }
    if ((showClose || item.showClose) && item.closeRect.contains(x, y)) {
      return SWT.getMessage("SWT_Close");
    }
    return item.getToolTipText();
  }
}
