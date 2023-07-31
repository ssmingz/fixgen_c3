class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    super.setText(string);
    String accelString = "";
    int index = string.indexOf('\t');
    if (index != (-1)) {
      accelString = string.substring(index, string.length());
      string = string.substring(0, index);
    }
    char[] chars = fixMnemonic(string);
    byte[] buffer = Converter.wcsToMbcs(null, chars, true);
    int label = OS.gtk_bin_get_child(handle);
    OS.gtk_label_set_text_with_mnemonic(label, buffer);
    buffer = Converter.wcsToMbcs(null, accelString, true);
    int ptr = OS.g_malloc(buffer.length);
    OS.memmove(ptr, buffer, buffer.length);
    int oldPtr = OS.GTK_ACCEL_LABEL_ACCEL_STRING(label);
    OS.GTK_ACCEL_LABEL_ACCEL_STRING(label, ptr);
    if (oldPtr != 0) {
      OS.g_free(oldPtr);
    }
  }
}
