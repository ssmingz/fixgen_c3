class PlaceHold {
  String toolTipText(NMTTDISPINFO hdr) {
    long hwndToolTip = OS.SendMessage(handle, TVM_GETTOOLTIPS, 0, 0);
    if ((hwndToolTip == hdr.hwndFrom) && (toolTipText != null)) {
      return "";
    }
    if (headerToolTipHandle == hdr.hwndFrom) {
      for (int i = 0; i < columnCount; i++) {
        TreeColumn column = columns[i];
        if (column.id == hdr.idFrom) {
          return column.toolTipText;
        }
      }
      return super.toolTipText(hdr);
    }
    if (itemToolTipHandle == hdr.hwndFrom) {
      if (toolTipText != null) {
        return "";
      }
      int pos = OS.GetMessagePos();
      POINT pt = new POINT();
      OS.POINTSTOPOINT(pt, pos);
      OS.ScreenToClient(handle, pt);
      int[] index = new int[1];
      TreeItem[] item = new TreeItem[1];
      RECT[] cellRect = new RECT[1];
      RECT[] itemRect = new RECT[1];
      if (findCell(pt.x, pt.y, item, index, cellRect, itemRect)) {
        String text = null;
        if (index[0] == 0) {
          text = item[0].text;
        } else {
          String[] strings = item[0].strings;
          if (strings != null) {
            text = strings[index[0]];
          }
        }
        if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
          if (isCustomToolTip()) {
            text = " ";
          }
        }
        if (text != null) {
          return text;
        }
      }
    }
    return super.toolTipText(hdr);
  }
}
