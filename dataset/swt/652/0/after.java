class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    if (string.equals(this.text)) {
      return;
    }
    super.setText(string);
    if (labelHandle == 0) {
      return;
    }
    char[] chars = fixMnemonic(string);
    byte[] buffer = Converter.wcsToMbcs(null, chars, true);
    OS.gtk_label_set_text_with_mnemonic(labelHandle, buffer);
    if (string.length() != 0) {
      OS.gtk_widget_show(labelHandle);
    } else {
      OS.gtk_widget_hide(labelHandle);
    }
    parent.relayout();
  }
}
