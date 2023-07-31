class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.ARROW) != 0) {
      return;
    }
    text = string;
    char[] chars = fixMnemonic(string);
    byte[] buffer = Converter.wcsToMbcs(null, chars, true);
    OS.gtk_label_set_text_with_mnemonic(labelHandle, buffer);
    OS.gtk_widget_hide(imageHandle);
    OS.gtk_widget_show(labelHandle);
    GtkRequisition requisition = new GtkRequisition();
    OS.gtk_widget_size_request(handle, requisition);
  }
}
