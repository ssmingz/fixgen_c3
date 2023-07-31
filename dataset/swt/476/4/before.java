class PlaceHold {
  LRESULT WM_SIZE(int wParam, int lParam) {
    LRESULT result = super.WM_SIZE(wParam, lParam);
    if (isDisposed()) {
      return result;
    }
    int index = OS.SendMessage(handle, TCM_GETCURSEL, 0, 0);
    if (index != (-1)) {
      TabItem item = items[index];
      Control control = item.control;
      if ((control != null) && (!control.isDisposed())) {
        control.setBounds(getClientArea());
      }
    }
    return result;
  }
}
