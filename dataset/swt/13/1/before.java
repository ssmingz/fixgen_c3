class PlaceHold {
  String toolTipText(NMTTDISPINFO hdr) {
    int hwndToolTip = OS.SendMessage(handle, LVM_GETTOOLTIPS, 0, 0);
    if ((hwndToolTip == hdr.hwndFrom) && (toolTipText != null)) {
      return "";
    }
    if (headerToolTipHandle == hdr.hwndFrom) {
      int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
      int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
      if ((count == 1) && (columns[0] == null)) {
        count = 0;
      }
      for (int i = 0; i < count; i++) {
        TableColumn column = columns[i];
        if (column.id == hdr.idFrom) {
          return column.toolTipText;
        }
      }
    }
    return super.toolTipText(hdr);
  }
}
