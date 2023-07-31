class PlaceHold {
  LRESULT wmNotifyChild(NMHDR hdr, int wParam, int lParam) {
    int code = hdr.code;
    switch (code) {
      case OS.TCN_SELCHANGE:
      case OS.TCN_SELCHANGING:
        TabItem item = null;
        int index = ((int) (OS.SendMessage(handle, TCM_GETCURSEL, 0, 0)));
        if (index != (-1)) {
          item = items[index];
        }
        if (item != null) {
          Control control = item.control;
          if ((control != null) && (!control.isDisposed())) {
            if (code == OS.TCN_SELCHANGE) {
              control.setBounds(getClientArea());
            }
            control.setVisible(code == OS.TCN_SELCHANGE);
          }
        }
        if (code == OS.TCN_SELCHANGE) {
          Event event = new Event();
          event.item = item;
          postEvent(Selection, event);
        }
    }
    return super.wmNotifyChild(hdr, wParam, lParam);
  }
}
