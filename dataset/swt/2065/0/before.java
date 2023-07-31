class PlaceHold {
  public MenuItem getDefaultItem() {
    checkWidget();
    int id = OS.GetMenuDefaultItem(handle, MF_BYCOMMAND, GMDI_USEDISABLED);
    if (id == (-1)) {
      return null;
    }
    MENUITEMINFO info = new MENUITEMINFO();
    info.cbSize = MENUITEMINFO.sizeof;
    info.fMask = OS.MIIM_ID;
    if (OS.GetMenuItemInfo(handle, id, false, info)) {
      return parent.findMenuItem(info.wID);
    }
    return null;
  }
}
