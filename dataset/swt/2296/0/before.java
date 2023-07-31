class PlaceHold {
  GtkBorder getGtkBorderPadding() {
    if (OS.GTK3) {
      GtkBorder gtkBorderPadding = new GtkBorder();
      long context = OS.gtk_widget_get_style_context(textEntryHandle);
      OS.gtk_style_context_get_padding(context, GTK_STATE_FLAG_NORMAL, gtkBorderPadding);
      return gtkBorderPadding;
    } else {
      GtkBorder padding = new GtkBorder();
      padding.bottom = padding.top = padding.left = padding.right = GTK2_MANUAL_BORDER_PADDING;
      return padding;
    }
  }
}
