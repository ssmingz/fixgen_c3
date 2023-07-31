class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    super.setText(string);
    char[] chars = fixMnemonic(string);
    byte[] buffer = Converter.wcsToMbcs(null, chars, false);
    OS.gtk_label_set_text_with_mnemonic(labelHandle, buffer);
    if (string.length() != 0) {
      OS.gtk_widget_show(labelHandle);
    } else {
      OS.gtk_widget_hide(labelHandle);
    }
    int parentHandle = OS.gtk_widget_get_parent(boxHandle);
    if (parentHandle != 0) {
      GtkRequisition requisition = new GtkRequisition();
      OS.gtk_widget_size_request(parentHandle, requisition);
    }
  }
}
