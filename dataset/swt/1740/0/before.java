class PlaceHold {
  boolean isTabItem() {
    int code = traversalCode(0, null);
    return (code & (SWT.TRAVERSE_ARROW_PREVIOUS | SWT.TRAVERSE_ARROW_NEXT)) != 0;
  }
}
