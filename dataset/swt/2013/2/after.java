class PlaceHold {
  LRESULT WM_CHAR(int wParam, int lParam) {
    LRESULT result = super.WM_CHAR(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((OS.GetKeyState(VK_CONTROL) < 0) && (OS.GetKeyState(VK_SHIFT) >= 0)) {
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      if ((bits & OS.LBS_EXTENDEDSEL) != 0) {
        switch (((int) (wParam))) {
          case OS.VK_SPACE:
            {
              int index = ((int) (OS.SendMessage(handle, LB_GETCARETINDEX, 0, 0)));
              int code = ((int) (OS.SendMessage(handle, LB_GETSEL, index, 0)));
              if (code == OS.LB_ERR) {
                break;
              }
              OS.SendMessage(handle, LB_SETSEL, code != 0 ? 0 : 1, index);
              OS.SendMessage(handle, LB_SETANCHORINDEX, index, 0);
              sendSelectionEvent(Selection);
              return LRESULT.ZERO;
            }
        }
      }
    }
    return result;
  }
}
