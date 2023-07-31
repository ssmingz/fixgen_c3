class PlaceHold {
  public void addAccessibleHyperlinkListener(AccessibleHyperlinkListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (accessibleHyperlinkListeners == null) {
      accessibleHyperlinkListeners = new ArrayList<AccessibleHyperlinkListener>();
    }
    accessibleHyperlinkListeners.add(listener);
  }
}
