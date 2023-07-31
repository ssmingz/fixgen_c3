class PlaceHold {
  String toolTipText(NMTTDISPINFO hdr) {
    int hwndToolTip = OS.SendMessage(handle, LVM_GETTOOLTIPS, 0, 0);
    if ((hwndToolTip == hdr.hwndFrom) && (toolTipText != null)) {
      return "";
    }
    if (headerToolTipHandle == hdr.hwndFrom) {
      for (int i = 0; i < columnCount; i++) {
        TableColumn column = columns[i];
        if (column.id == hdr.idFrom) {
          return column.toolTipText;
        }
      }
    }
    return super.toolTipText(hdr);
  }
}
