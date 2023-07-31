class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    text = string;
    if ((style & SWT.ARROW) != 0) {
      return;
    }
    int length = string.length();
    char[] text = new char[length + 1];
    string.getChars(0, length, text, 0);
    for (int i = 0; i < length; i++) {
      if (text[i] == '&') {
        text[i] = '_';
      }
    }
    int list = OS.gtk_container_get_children(handle);
    if (list != 0) {
      int widget = OS.g_list_nth_data(list, 0);
      if (widget != 0) {
        OS.gtk_widget_destroy(widget);
      }
      OS.g_list_free(list);
    }
    byte[] buffer = Converter.wcsToMbcs(null, text);
    int label = OS.gtk_label_new_with_mnemonic(buffer);
    OS.gtk_container_add(handle, label);
    OS.gtk_widget_show(label);
  }
}
