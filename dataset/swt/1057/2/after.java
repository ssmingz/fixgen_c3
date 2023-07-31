class PlaceHold {
  public boolean getVisible() {
    checkWidget();
    if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(4, 10))) {
      SCROLLBARINFO psbi = new SCROLLBARINFO();
      psbi.cbSize = SCROLLBARINFO.sizeof;
      int idObject = ((style & SWT.VERTICAL) != 0) ? OS.OBJID_VSCROLL : OS.OBJID_HSCROLL;
      OS.GetScrollBarInfo(hwndScrollBar(), idObject, psbi);
      return (psbi.rgstate[0] & OS.STATE_SYSTEM_INVISIBLE) == 0;
    }
    return (state & HIDDEN) == 0;
  }
}
