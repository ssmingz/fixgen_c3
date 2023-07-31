class PlaceHold {
  void createDisplay(DeviceData data) {
    if (!OS.g_thread_supported()) {
      OS.g_thread_init(0);
    }
    if (OS.GTK_VERSION < OS.VERSION(2, 24, 0)) {
      OS.gtk_set_locale();
    }
    if (!OS.gtk_init_check(new int[] {0}, null)) {
      SWT.error(ERROR_NO_HANDLES, null, " [gtk_init_check() failed]");
    }
    if (OS.GDK_WINDOWING_X11()) {
      xDisplay = OS.gdk_x11_display_get_xdisplay(OS.gdk_display_get_default());
    }
    int ptr = OS.gtk_check_version(MAJOR, MINOR, MICRO);
    if (ptr != 0) {
      int length = OS.strlen(ptr);
      byte[] buffer = new byte[length];
      OS.memmove(buffer, ptr, length);
      System.out.println("***WARNING: " + new String(Converter.mbcsToWcs(null, buffer)));
      System.out.println(
          (((("***WARNING: SWT requires GTK " + MAJOR) + ".") + MINOR) + ".") + MICRO);
      int major = OS.gtk_major_version();
      int minor = OS.gtk_minor_version();
      int micro = OS.gtk_micro_version();
      System.out.println((((("***WARNING: Detected: " + major) + ".") + minor) + ".") + micro);
    }
    if (fixed_type == 0) {
      byte[] type_name = Converter.wcsToMbcs(null, "SwtFixed", true);
      fixedClassInitCallback = new Callback(getClass(), "fixedClassInitProc", 2);
      fixedClassInitProc = fixedClassInitCallback.getAddress();
      if (fixedClassInitProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      fixedMapCallback = new Callback(getClass(), "fixedMapProc", 1);
      fixedMapProc = fixedMapCallback.getAddress();
      if (fixedMapProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      fixedSizeAllocateCallback = new Callback(getClass(), "fixedSizeAllocateProc", 2);
      fixedSizeAllocateProc = fixedSizeAllocateCallback.getAddress();
      if (fixedSizeAllocateProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      GTypeInfo fixed_info = new GTypeInfo();
      fixed_info.class_size = ((short) (OS.GtkFixedClass_sizeof()));
      fixed_info.class_init = fixedClassInitProc;
      fixed_info.instance_size = ((short) (OS.GtkFixed_sizeof()));
      fixed_info_ptr = OS.g_malloc(sizeof);
      OS.memmove(fixed_info_ptr, fixed_info, sizeof);
      fixed_type = OS.g_type_register_static(OS.GTK_TYPE_FIXED(), type_name, fixed_info_ptr, 0);
    }
    if (rendererClassInitProc == 0) {
      rendererClassInitCallback = new Callback(getClass(), "rendererClassInitProc", 2);
      rendererClassInitProc = rendererClassInitCallback.getAddress();
      if (rendererClassInitProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
    }
    if (rendererRenderProc == 0) {
      rendererRenderCallback = new Callback(getClass(), "rendererRenderProc", 7);
      rendererRenderProc = rendererRenderCallback.getAddress();
      if (rendererRenderProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
    }
    if (rendererGetSizeProc == 0) {
      rendererGetSizeCallback = new Callback(getClass(), "rendererGetSizeProc", 7);
      rendererGetSizeProc = rendererGetSizeCallback.getAddress();
      if (rendererGetSizeProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
    }
    if (text_renderer_type == 0) {
      GTypeInfo renderer_info = new GTypeInfo();
      renderer_info.class_size = ((short) (OS.GtkCellRendererTextClass_sizeof()));
      renderer_info.class_init = rendererClassInitProc;
      renderer_info.instance_size = ((short) (OS.GtkCellRendererText_sizeof()));
      text_renderer_info_ptr = OS.g_malloc(sizeof);
      OS.memmove(text_renderer_info_ptr, renderer_info, sizeof);
      byte[] type_name = Converter.wcsToMbcs(null, "SwtTextRenderer", true);
      text_renderer_type =
          OS.g_type_register_static(
              OS.GTK_TYPE_CELL_RENDERER_TEXT(), type_name, text_renderer_info_ptr, 0);
    }
    if (pixbuf_renderer_type == 0) {
      GTypeInfo renderer_info = new GTypeInfo();
      renderer_info.class_size = ((short) (OS.GtkCellRendererPixbufClass_sizeof()));
      renderer_info.class_init = rendererClassInitProc;
      renderer_info.instance_size = ((short) (OS.GtkCellRendererPixbuf_sizeof()));
      pixbuf_renderer_info_ptr = OS.g_malloc(sizeof);
      OS.memmove(pixbuf_renderer_info_ptr, renderer_info, sizeof);
      byte[] type_name = Converter.wcsToMbcs(null, "SwtPixbufRenderer", true);
      pixbuf_renderer_type =
          OS.g_type_register_static(
              OS.GTK_TYPE_CELL_RENDERER_PIXBUF(), type_name, pixbuf_renderer_info_ptr, 0);
    }
    if (toggle_renderer_type == 0) {
      GTypeInfo renderer_info = new GTypeInfo();
      renderer_info.class_size = ((short) (OS.GtkCellRendererToggleClass_sizeof()));
      renderer_info.class_init = rendererClassInitProc;
      renderer_info.instance_size = ((short) (OS.GtkCellRendererToggle_sizeof()));
      toggle_renderer_info_ptr = OS.g_malloc(sizeof);
      OS.memmove(toggle_renderer_info_ptr, renderer_info, sizeof);
      byte[] type_name = Converter.wcsToMbcs(null, "SwtToggleRenderer", true);
      toggle_renderer_type =
          OS.g_type_register_static(
              OS.GTK_TYPE_CELL_RENDERER_TOGGLE(), type_name, toggle_renderer_info_ptr, 0);
    }
    OS.gtk_widget_set_default_direction(GTK_TEXT_DIR_LTR);
    byte[] buffer = Converter.wcsToMbcs(null, APP_NAME, true);
    OS.g_set_prgname(buffer);
    OS.gdk_set_program_class(buffer);
    byte[] flatStyle =
        Converter.wcsToMbcs(
            null,
            "style \"swt-flat\" { GtkToolbar::shadow-type = none } widget \"*.swt-toolbar-flat\""
                + " style : highest \"swt-flat\"",
            true);
    OS.gtk_rc_parse_string(flatStyle);
    shellHandle = OS.gtk_window_new(GTK_WINDOW_TOPLEVEL);
    if (shellHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_widget_realize(shellHandle);
    eventCallback = new Callback(this, "eventProc", 2);
    eventProc = eventCallback.getAddress();
    if (eventProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    OS.gdk_event_handler_set(eventProc, 0, 0);
    filterCallback = new Callback(this, "filterProc", 3);
    filterProc = filterCallback.getAddress();
    if (filterProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    OS.gdk_window_add_filter(0, filterProc, 0);
    if (OS.GDK_WINDOWING_X11()) {
      int xWindow = OS.gdk_x11_drawable_get_xid(OS.GTK_WIDGET_WINDOW(shellHandle));
      byte[] atomName = Converter.wcsToMbcs(null, "SWT_Window_" + APP_NAME, true);
      int atom = OS.XInternAtom(xDisplay, atomName, false);
      OS.XSetSelectionOwner(xDisplay, atom, xWindow, CurrentTime);
      OS.XGetSelectionOwner(xDisplay, atom);
    }
    signalCallback = new Callback(this, "signalProc", 3);
    signalProc = signalCallback.getAddress();
    if (signalProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    OS.gtk_widget_add_events(shellHandle, GDK_PROPERTY_CHANGE_MASK);
    OS.g_signal_connect(shellHandle, property_notify_event, signalProc, PROPERTY_NOTIFY);
  }
}
