class PlaceHold {
  LRESULT CDDS_ITEMPOSTPAINT(NMLVCUSTOMDRAW nmcd, int wParam, int lParam) {
    int hDC = nmcd.hdc;
    if (explorerTheme && (!ignoreCustomDraw)) {
      hotIndex = -1;
      if (hooks(EraseItem) && (nmcd.left != nmcd.right)) {
        OS.RestoreDC(hDC, -1);
      }
    }
    if (((!ignoreCustomDraw) && (!ignoreDrawFocus)) && (nmcd.left != nmcd.right)) {
      if (OS.IsWindowVisible(handle) && OS.IsWindowEnabled(handle)) {
        if ((!explorerTheme) && ((style & SWT.FULL_SELECTION) != 0)) {
          if (OS.SendMessage(handle, LVM_GETBKCOLOR, 0, 0) == OS.CLR_NONE) {
            int dwExStyle = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
            if ((dwExStyle & OS.LVS_EX_FULLROWSELECT) == 0) {
              if (OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED) == nmcd.dwItemSpec) {
                if (handle == OS.GetFocus()) {
                  int uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
                  if ((uiState & OS.UISF_HIDEFOCUS) == 0) {
                    RECT rect = new RECT();
                    rect.left = OS.LVIR_BOUNDS;
                    boolean oldIgnore = ignoreCustomDraw;
                    ignoreCustomDraw = true;
                    OS.SendMessage(handle, LVM_GETITEMRECT, nmcd.dwItemSpec, rect);
                    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
                    int index = ((int) (OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, 0, 0)));
                    RECT itemRect = new RECT();
                    if (index == 0) {
                      itemRect.left = OS.LVIR_LABEL;
                      OS.SendMessage(handle, LVM_GETITEMRECT, index, itemRect);
                    } else {
                      itemRect.top = index;
                      itemRect.left = OS.LVIR_ICON;
                      OS.SendMessage(handle, LVM_GETSUBITEMRECT, nmcd.dwItemSpec, itemRect);
                    }
                    ignoreCustomDraw = oldIgnore;
                    rect.left = itemRect.left;
                    OS.DrawFocusRect(nmcd.hdc, rect);
                  }
                }
              }
            }
          }
        }
      }
    }
    return null;
  }
}
