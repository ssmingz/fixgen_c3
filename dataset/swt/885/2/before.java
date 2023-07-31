class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    text = string;
    char[] chars = fixMnemonic(string);
    byte[] buffer = Converter.wcsToMbcs(null, chars, false);
    OS.gtk_label_set_text_with_mnemonic(labelHandle, buffer);
    if (string.length() != 0) {
      OS.gtk_frame_set_label_widget(handle, labelHandle);
      OS.gtk_widget_show(labelHandle);
    } else {
      OS.gtk_frame_set_label_widget(handle, 0);
      OS.gtk_widget_hide(labelHandle);
    }
    fixGroup();
  }
}
