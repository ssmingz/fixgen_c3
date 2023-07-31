class PlaceHold {
  LRESULT WM_LBUTTONUP(int wParam, int lParam) {
    LRESULT result = super.WM_LBUTTONUP(wParam, lParam);
    if (result == LRESULT.ZERO) {
      return result;
    }
    if (focusItem == null) {
      return result;
    }
    int x = ((short) (lParam & 0xffff));
    int y = ((short) (lParam >> 16));
    boolean hover = focusItem.isHover(x, y);
    if (hover) {
      Event event = new Event();
      event.item = focusItem;
      sendEvent(focusItem.expanded ? SWT.Collapse : SWT.Expand, event);
      focusItem.expanded = !focusItem.expanded;
      showItem(focusItem);
    }
    return result;
  }
}
