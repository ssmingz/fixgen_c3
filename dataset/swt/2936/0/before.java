class PlaceHold {
  boolean setScrollWidth(TableItem item, boolean force) {
    if (currentItem != null) {
      if (currentItem != item) {
        fixScrollWidth = true;
      }
      return false;
    }
    if ((!force) && ((drawCount != 0) || (!OS.IsWindowVisible(handle)))) {
      fixScrollWidth = true;
      return false;
    }
    fixScrollWidth = false;
    if (columnCount == 0) {
      int newWidth = 0;
      int imageIndent = 0;
      int index = 0;
      int itemCount = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
      while (index < itemCount) {
        String string = null;
        int hFont = -1;
        if (item != null) {
          string = item.text;
          imageIndent = Math.max(imageIndent, item.imageIndent);
          hFont = item.fontHandle(0);
        } else if (items[index] != null) {
          TableItem tableItem = items[index];
          string = tableItem.text;
          imageIndent = Math.max(imageIndent, tableItem.imageIndent);
          hFont = tableItem.fontHandle(0);
        }
        if ((string != null) && (string.length() != 0)) {
          if (hFont != (-1)) {
            int hDC = OS.GetDC(handle);
            int oldFont = OS.SelectObject(hDC, hFont);
            int flags = (OS.DT_CALCRECT | OS.DT_SINGLELINE) | OS.DT_NOPREFIX;
            TCHAR buffer = new TCHAR(getCodePage(), string, false);
            RECT rect = new RECT();
            OS.DrawText(hDC, buffer, buffer.length(), rect, flags);
            OS.SelectObject(hDC, oldFont);
            OS.ReleaseDC(handle, hDC);
            newWidth = Math.max(newWidth, rect.right - rect.left);
          } else {
            TCHAR buffer = new TCHAR(getCodePage(), string, true);
            newWidth =
                Math.max(newWidth, ((int) (OS.SendMessage(handle, LVM_GETSTRINGWIDTH, 0, buffer))));
          }
        }
        if (item != null) {
          break;
        }
        index++;
      }
      if (newWidth == 0) {
        TCHAR buffer = new TCHAR(getCodePage(), " ", true);
        newWidth =
            Math.max(newWidth, ((int) (OS.SendMessage(handle, LVM_GETSTRINGWIDTH, 0, buffer))));
      }
      int hStateList = OS.SendMessage(handle, LVM_GETIMAGELIST, LVSIL_STATE, 0);
      if (hStateList != 0) {
        int[] cx = new int[1];
        int[] cy = new int[1];
        OS.ImageList_GetIconSize(hStateList, cx, cy);
        newWidth += cx[0] + INSET;
      }
      int hImageList = OS.SendMessage(handle, LVM_GETIMAGELIST, LVSIL_SMALL, 0);
      if (hImageList != 0) {
        int[] cx = new int[1];
        int[] cy = new int[1];
        OS.ImageList_GetIconSize(hImageList, cx, cy);
        newWidth += (imageIndent + 1) * cx[0];
      } else {
        newWidth++;
      }
      newWidth += INSET * 2;
      int oldWidth = ((int) (OS.SendMessage(handle, LVM_GETCOLUMNWIDTH, 0, 0)));
      if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
        newWidth += VISTA_EXTRA;
      }
      if (newWidth > oldWidth) {
        setScrollWidth(newWidth);
        return true;
      }
    }
    return false;
  }
}
