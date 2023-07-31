class PlaceHold {
  public void pack() {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    int hwnd = parent.handle;
    TCHAR buffer = new TCHAR(parent.getCodePage(), text, true);
    int headerWidth = OS.SendMessage(hwnd, LVM_GETSTRINGWIDTH, 0, buffer) + 10;
    if (image != null) {
      int margin = 0;
      if (((OS.COMCTL32_MAJOR << 16) | OS.COMCTL32_MINOR) >= ((5 << 16) | 80)) {
        int hwndHeader = OS.SendMessage(hwnd, LVM_GETHEADER, 0, 0);
        margin = OS.SendMessage(hwndHeader, HDM_GETBITMAPMARGIN, 0, 0);
      } else {
        margin = OS.GetSystemMetrics(SM_CXEDGE) * 3;
      }
      Rectangle rect = image.getBounds();
      headerWidth += rect.width + (margin * 2);
    }
    if ((parent.style & SWT.VIRTUAL) != 0) {
      if (image == null) {
        OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, LVSCW_AUTOSIZE_USEHEADER);
      } else {
        OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, headerWidth);
      }
    } else {
      OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, LVSCW_AUTOSIZE);
      int columnWidth = OS.SendMessage(hwnd, LVM_GETCOLUMNWIDTH, index, 0);
      if ((index == 0) && (parent.imageList == null)) {
        columnWidth += 2;
      }
      if (headerWidth > columnWidth) {
        if (image == null) {
          OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, LVSCW_AUTOSIZE_USEHEADER);
        } else {
          OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, headerWidth);
        }
      } else if (index == 0) {
        OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, columnWidth);
      }
    }
  }
}
