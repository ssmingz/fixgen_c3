class TextLayout {
  public TextLayout(Device device) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    context = OS.gdk_pango_context_get();
    if (context == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.pango_context_set_language(context, OS.gtk_get_default_language());
    OS.pango_context_set_base_dir(context, PANGO_DIRECTION_LTR);
    OS.gdk_pango_context_set_colormap(context, OS.gdk_colormap_get_system());
    layout = OS.pango_layout_new(context);
    if (layout == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.pango_layout_set_wrap(layout, PANGO_WRAP_WORD_CHAR);
    OS.pango_layout_set_tabs(layout, device.emptyTab);
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      OS.pango_layout_set_auto_dir(layout, false);
    }
    text = "";
    ascent = descent = -1;
    styles = new StyleItem[2];
    styles[0] = new StyleItem();
    styles[1] = new StyleItem();
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
