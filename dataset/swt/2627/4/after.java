class PlaceHold {
  Rect getInset() {
    if ((style & SWT.SEARCH) != 0) {
      return display.searchTextInset;
    }
    if (txnObject != 0) {
      return super.getInset();
    }
    return display.editTextInset;
  }
}
