class PlaceHold {
  public void addCTabFolderCloseListener(CTabFolderCloseListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (closeListeners.length == 0) {
      showClose = true;
      updateItems();
      redraw();
    }
    CTabFolderCloseListener[] newListeners = new CTabFolderCloseListener[closeListeners.length + 1];
    System.arraycopy(closeListeners, 0, newListeners, 0, closeListeners.length);
    closeListeners = newListeners;
    closeListeners[closeListeners.length - 1] = listener;
  }
}
