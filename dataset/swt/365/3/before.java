class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    super.setText(string);
    updateText(((short) (index + 1)));
  }
}
