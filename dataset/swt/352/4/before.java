class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    text = string;
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    char[] chars = fixMnemonic(string);
    byte[] buffer = Converter.wcsToMbcs(null, chars);
    OS.gtk_label_set_text_with_mnemonic(labelHandle, buffer);
    OS.gtk_widget_hide(pixmapHandle);
    if (string.length() != 0) {
      OS.gtk_widget_show(labelHandle);
    } else {
      OS.gtk_widget_hide(labelHandle);
    }
  }
}
