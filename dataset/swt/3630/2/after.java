class PlaceHold {
  LRESULT WM_SIZE(int wParam, int lParam) {
    if (ignoreResize) {
      return null;
    }
    if ((style & SWT.SIMPLE) != 0) {
      LRESULT result = super.WM_SIZE(wParam, lParam);
      if (OS.IsWindowVisible(handle)) {
        if (OS.IsWinCE) {
          int hwndText = OS.GetDlgItem(handle, CBID_EDIT);
          if (hwndText != 0) {
            OS.InvalidateRect(hwndText, null, true);
          }
          int hwndList = OS.GetDlgItem(handle, CBID_LIST);
          if (hwndList != 0) {
            OS.InvalidateRect(hwndList, null, true);
          }
        } else {
          int uFlags = (OS.RDW_ERASE | OS.RDW_INVALIDATE) | OS.RDW_ALLCHILDREN;
          OS.RedrawWindow(handle, null, 0, uFlags);
        }
      }
      return result;
    }
    boolean oldLockText = lockText;
    if ((style & SWT.READ_ONLY) == 0) {
      lockText = true;
    }
    LRESULT result = super.WM_SIZE(wParam, lParam);
    if ((style & SWT.READ_ONLY) == 0) {
      lockText = oldLockText;
    }
    if ((style & SWT.H_SCROLL) != 0) {
      setScrollWidth(scrollWidth);
    }
    return result;
  }
}
