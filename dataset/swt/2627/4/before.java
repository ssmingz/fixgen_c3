class PlaceHold {
  Rect getInset() {
    if (txnObject != 0) {
      return super.getInset();
    }
    return display.editTextInset;
  }
}
