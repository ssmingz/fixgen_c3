class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    if ((style & SWT.READ_ONLY) == 0) {
      ((NSControl) (view)).abortEditing();
    }
    selectionRange = null;
  }
}
