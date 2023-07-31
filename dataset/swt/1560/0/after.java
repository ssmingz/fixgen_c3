class PlaceHold {
  public void paste() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      return;
    }
    textView.window().fieldEditor(false, textView).paste(null);
  }
}
