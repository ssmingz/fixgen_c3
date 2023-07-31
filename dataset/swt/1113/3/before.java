class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    text = string;
    if ((style & SWT.BALLOON) == 0) {
      return;
    }
    if (layoutText != 0) {
      OS.g_object_unref(layoutText);
    }
    layoutText = 0;
    if (text.length() != 0) {
      byte[] buffer = Converter.wcsToMbcs(null, text, true);
      layoutText = OS.gtk_widget_create_pango_layout(handle, buffer);
      OS.pango_layout_set_auto_dir(layoutText, false);
      long boldAttr = OS.pango_attr_weight_new(PANGO_WEIGHT_BOLD);
      PangoAttribute attribute = new PangoAttribute();
      OS.memmove(attribute, boldAttr, sizeof);
      attribute.start_index = 0;
      attribute.end_index = buffer.length;
      OS.memmove(boldAttr, attribute, sizeof);
      long attrList = OS.pango_attr_list_new();
      OS.pango_attr_list_insert(attrList, boldAttr);
      OS.pango_layout_set_attributes(layoutText, attrList);
      OS.pango_attr_list_unref(attrList);
      OS.pango_layout_set_wrap(layoutText, PANGO_WRAP_WORD_CHAR);
    }
    if (gtk_widget_get_visible(handle)) {
      configure();
    }
  }
}
