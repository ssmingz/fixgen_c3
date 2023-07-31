class PlaceHold {
  String toolTipText(NMTTDISPINFO hdr) {
    if ((hdr.uFlags & OS.TTF_IDISHWND) != 0) {
      return null;
    }
    if (!hasCursor()) {
      return "";
    }
    int index = ((int) (hdr.idFrom));
    long hwndToolTip = OS.SendMessage(handle, TB_GETTOOLTIPS, 0, 0);
    if (hwndToolTip == hdr.hwndFrom) {
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        hdr.uFlags |= OS.TTF_RTLREADING;
      } else {
        hdr.uFlags &= ~OS.TTF_RTLREADING;
      }
      if (toolTipText != null) {
        return "";
      }
      if ((0 <= index) && (index < items.length)) {
        ToolItem item = items[index];
        if (item != null) {
          if (lastArrowId != (-1)) {
            return "";
          }
          return item.toolTipText;
        }
      }
    }
    return super.toolTipText(hdr);
  }
}
