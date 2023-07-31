class PlaceHold {
  public void cut() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      return;
    }
    textView.window().fieldEditor(false, textView).cut(null);
  }
}
