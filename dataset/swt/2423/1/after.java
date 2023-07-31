class PlaceHold {
  LRESULT wmNotifyChild(int wParam, int lParam) {
    NMHDR hdr = new NMHDR();
    OS.MoveMemory(hdr, lParam, sizeof);
    switch (hdr.code) {
      case OS.UDN_DELTAPOS:
        NMUPDOWN lpnmud = new NMUPDOWN();
        OS.MoveMemory(lpnmud, lParam, sizeof);
        int value = lpnmud.iPos + lpnmud.iDelta;
        int[] max = new int[1];
        int[] min = new int[1];
        OS.SendMessage(hwndUpDown, UDM_GETRANGE32, min, max);
        if ((style & SWT.WRAP) != 0) {
          if (value < min[0]) {
            value = max[0];
          }
          if (value > max[0]) {
            value = min[0];
          }
        }
        value = Math.min(Math.max(min[0], value), max[0]);
        setSelection(value, true);
        return LRESULT.ONE;
    }
    return super.wmNotifyChild(wParam, lParam);
  }
}
