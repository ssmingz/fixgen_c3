class PlaceHold {
  void onFocusOut() {
    if (focusItem != null) {
      redrawItem(focusItem.index, true);
    }
    if ((style & (SWT.HIDE_SELECTION | SWT.MULTI)) == (SWT.HIDE_SELECTION | SWT.MULTI)) {
      for (int i = 0; i < selectedItems.length; i++) {
        redrawItem(selectedItems[i].index, true);
      }
    }
  }
}
