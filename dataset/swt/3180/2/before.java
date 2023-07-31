class PlaceHold {
  LRESULT WM_CHAR(int wParam, int lParam) {
    LRESULT result = super.WM_CHAR(wParam, lParam);
    if (result != null) {
      return result;
    }
    switch (((int) (wParam))) {
      case ' ':
        if ((style & SWT.CHECK) != 0) {
          int index = ((int) (OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED)));
          if (index != (-1)) {
            TableItem item = _getItem(index);
            item.setChecked(!item.getChecked(), true);
            if (!OS.IsWinCE) {
              OS.NotifyWinEvent(EVENT_OBJECT_FOCUS, handle, OBJID_CLIENT, index + 1);
            }
          }
        }
        int code = callWindowProc(handle, WM_KEYDOWN, wParam, lParam);
        return new LRESULT(code);
      case SWT.CR:
        int index = ((int) (OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED)));
        if (index != (-1)) {
          Event event = new Event();
          event.item = _getItem(index);
          postEvent(DefaultSelection, event);
        }
        return LRESULT.ZERO;
    }
    return result;
  }
}
