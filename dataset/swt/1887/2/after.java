class PlaceHold {
  public void addCTabFolderExpandListener(CTabFolderExpandListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (expandListeners.length == 0) {
      showExpand = true;
      setButtonBounds();
      updateItems();
      redrawTabArea();
    }
    CTabFolderExpandListener[] newListeners =
        new CTabFolderExpandListener[expandListeners.length + 1];
    System.arraycopy(expandListeners, 0, newListeners, 0, expandListeners.length);
    expandListeners = newListeners;
    expandListeners[expandListeners.length - 1] = listener;
  }
}
