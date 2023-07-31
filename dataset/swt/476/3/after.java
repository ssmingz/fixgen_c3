class PlaceHold {
  LRESULT WM_CAPTURECHANGED(int wParam, int lParam) {
    LRESULT result = super.WM_CAPTURECHANGED(wParam, lParam);
    if (result != null) {
      return result;
    }
    for (int i = 0; i < items.length; i++) {
      ToolItem item = items[i];
      if (item != null) {
        int fsState = ((int) (OS.SendMessage(handle, TB_GETSTATE, item.id, 0)));
        if ((fsState & OS.TBSTATE_PRESSED) != 0) {
          fsState &= ~OS.TBSTATE_PRESSED;
          OS.SendMessage(handle, TB_SETSTATE, item.id, fsState);
        }
      }
    }
    return result;
  }
}
