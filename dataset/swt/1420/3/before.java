class PlaceHold {
  boolean setTabItemFocus() {
    if (parent.setTabItemFocus()) {
      int hwnd = parent.handle;
      int index = ((int) (OS.SendMessage(hwnd, TB_COMMANDTOINDEX, id, 0)));
      OS.SendMessage(hwnd, TB_SETHOTITEM, index, 0);
      return true;
    }
    return false;
  }
}
