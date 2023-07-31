class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    if ((style & SWT.SINGLE) != 0) {
      ((NSControl) (view)).abortEditing();
    }
    hiddenText = message = null;
    selectionRange = null;
  }
}
