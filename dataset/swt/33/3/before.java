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
    if (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) < ((4 << 16) | 10)) {
      return;
    }
    MENUITEMINFO info = new MENUITEMINFO();
    info.cbSize = MENUITEMINFO.sizeof;
    info.fMask = OS.MIIM_BITMAP;
    if (image != null) {
      info.hbmpItem = OS.HBMMENU_CALLBACK;
    }
    int hMenu = parent.handle;
    OS.SetMenuItemInfo(hMenu, id, false, info);
    parent.redraw();
  }
}
