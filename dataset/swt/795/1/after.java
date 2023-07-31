class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    super.setImage(image);
    if (OS.IsWinCE) {
      if ((OS.IsPPC || OS.IsSP) && (parent.hwndCB != 0)) {
        int hwndCB = parent.hwndCB;
        TBBUTTONINFO info = new TBBUTTONINFO();
        info.cbSize = TBBUTTONINFO.sizeof;
        info.dwMask = OS.TBIF_IMAGE;
        info.iImage = parent.imageIndex(image);
        OS.SendMessage(hwndCB, TB_SETBUTTONINFO, id, info);
      }
      return;
    }
    if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
      return;
    }
    MENUITEMINFO info = new MENUITEMINFO();
    info.cbSize = MENUITEMINFO.sizeof;
    info.fMask = OS.MIIM_BITMAP;
    if (parent.foreground != (-1)) {
      info.hbmpItem = OS.HBMMENU_CALLBACK;
    } else if (((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) && OS.IsAppThemed()) {
      if (hBitmap != 0) {
        OS.DeleteObject(hBitmap);
      }
      info.hbmpItem = hBitmap = (image != null) ? Display.create32bitDIB(image) : 0;
    } else {
      info.hbmpItem = (image != null) ? OS.HBMMENU_CALLBACK : 0;
    }
    int hMenu = parent.handle;
    OS.SetMenuItemInfo(hMenu, id, false, info);
    parent.redraw();
  }
}
