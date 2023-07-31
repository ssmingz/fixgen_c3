class PlaceHold {
  public boolean getSelection() {
    checkWidget();
    if ((style & (SWT.CHECK | SWT.RADIO)) == 0) {
      return false;
    }
    if (OS.IsPPC && (parent.hwndCB != 0)) {
      return false;
    }
    int hMenu = parent.handle;
    MENUITEMINFO info = new MENUITEMINFO();
    info.cbSize = MENUITEMINFO.sizeof;
    info.fMask = OS.MIIM_STATE;
    boolean success = OS.GetMenuItemInfo(hMenu, id, false, info);
    if (!success) {
      error(ERROR_CANNOT_GET_SELECTION);
    }
    return (info.fState & OS.MFS_CHECKED) != 0;
  }
}
