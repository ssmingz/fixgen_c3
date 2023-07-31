class PlaceHold {
  boolean mnemonicHit(char ch) {
    int key = wcsToMbcs(ch);
    int[] id = new int[1];
    if (OS.SendMessage(handle, TB_MAPACCELERATOR, key, id) == 0) {
      return false;
    }
    if (!setTabGroupFocus()) {
      return false;
    }
    int index = OS.SendMessage(handle, TB_COMMANDTOINDEX, id[0], 0);
    if (index == (-1)) {
      return false;
    }
    OS.SendMessage(handle, TB_SETHOTITEM, index, 0);
    items[id[0]].click(false);
    return true;
  }
}
