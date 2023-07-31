class PlaceHold {
  LRESULT WM_CHAR(int wParam, int lParam) {
    LRESULT result = super.WM_CHAR(wParam, lParam);
    if (result != null) {
      return result;
    }
    int[] id = new int[1];
    if (OS.SendMessage(handle, TB_MAPACCELERATOR, wParam, id) != 0) {
      int index = OS.SendMessage(handle, TB_COMMANDTOINDEX, id[0], 0);
      if (index != (-1)) {
        OS.SendMessage(handle, TB_SETHOTITEM, index, 0);
        items[index].click(false);
        return LRESULT.ZERO;
      }
    }
    return result;
  }
}
