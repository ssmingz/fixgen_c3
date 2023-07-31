class PlaceHold {
  LRESULT WM_SIZE(int wParam, int lParam) {
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
    LRESULT result = null;
    if ((style & SWT.READ_ONLY) != 0) {
      result = super.WM_SIZE(wParam, lParam);
    } else {
      int index = ((int) (OS.SendMessage(handle, CB_GETCURSEL, 0, 0)));
      boolean redraw = false;
      TCHAR buffer = null;
      int[] start = null;
      int[] end = null;
      if (index == OS.CB_ERR) {
        int length = OS.GetWindowTextLength(handle);
        if (length != 0) {
          buffer = new TCHAR(getCodePage(), length + 1);
          OS.GetWindowText(handle, buffer, length + 1);
          start = new int[1];
          end = new int[1];
          OS.SendMessage(handle, CB_GETEDITSEL, start, end);
          redraw = (drawCount == 0) && OS.IsWindowVisible(handle);
          if (redraw) {
            setRedraw(false);
          }
        }
      }
      result = super.WM_SIZE(wParam, lParam);
      if (isDisposed()) {
        return result;
      }
      if (buffer != null) {
        OS.SetWindowText(handle, buffer);
        int bits = OS.MAKELPARAM(start[0], end[0]);
        OS.SendMessage(handle, CB_SETEDITSEL, 0, bits);
        if (redraw) {
          setRedraw(true);
        }
      }
    }
    if ((style & SWT.H_SCROLL) != 0) {
      setScrollWidth(scrollWidth);
    }
    return result;
  }
}
