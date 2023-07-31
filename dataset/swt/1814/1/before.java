class PlaceHold {
  boolean isTabGroup() {
    int code = traversalCode(0, null);
    if ((code & (SWT.TRAVERSE_ARROW_PREVIOUS | SWT.TRAVERSE_ARROW_NEXT)) != 0) {
      return false;
    }
    return (code & (SWT.TRAVERSE_TAB_PREVIOUS | SWT.TRAVERSE_TAB_NEXT)) != 0;
  }
}
