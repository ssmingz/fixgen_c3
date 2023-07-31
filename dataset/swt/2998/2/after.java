class PlaceHold {
  LRESULT CDDS_ITEMPREPAINT(NMLVCUSTOMDRAW nmcd, int wParam, int lParam) {
    if (!ignoreCustomDraw) {
      if (OS.IsWindowVisible(handle) && OS.IsWindowEnabled(handle)) {
        if ((!explorerTheme) && ((style & SWT.FULL_SELECTION) != 0)) {
          if (((int) (OS.SendMessage(handle, LVM_GETBKCOLOR, 0, 0))) == OS.CLR_NONE) {
            int dwExStyle = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
            if ((dwExStyle & OS.LVS_EX_FULLROWSELECT) == 0) {
              if ((nmcd.uItemState & OS.CDIS_FOCUS) != 0) {
                nmcd.uItemState &= ~OS.CDIS_FOCUS;
                OS.MoveMemory(lParam, nmcd, sizeof);
              }
            }
          }
        }
      }
    }
    if (explorerTheme && (!ignoreCustomDraw)) {
      hotIndex = ((nmcd.uItemState & OS.CDIS_HOT) != 0) ? ((int) (nmcd.dwItemSpec)) : -1;
      if (hooks(EraseItem) && (nmcd.left != nmcd.right)) {
        OS.SaveDC(nmcd.hdc);
        int hrgn = OS.CreateRectRgn(0, 0, 0, 0);
        OS.SelectClipRgn(nmcd.hdc, hrgn);
        OS.DeleteObject(hrgn);
      }
    }
    return new LRESULT(OS.CDRF_NOTIFYSUBITEMDRAW | OS.CDRF_NOTIFYPOSTPAINT);
  }
}
