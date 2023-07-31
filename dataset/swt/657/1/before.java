class PlaceHold {
  LRESULT WM_KEYDOWN(int wParam, int lParam) {
    LRESULT result = super.WM_KEYDOWN(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (((style & SWT.CHECK) != 0) && (wParam == OS.VK_SPACE)) {
      int index = OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED);
      if (index != (-1)) {
        TableItem item = items[index];
        item.setChecked(!item.getChecked(), true);
      }
    }
    return result;
  }
}
