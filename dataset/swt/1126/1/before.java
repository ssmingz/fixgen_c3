class PlaceHold {
  int signalProc(int gobject, int arg1, int user_data) {
    switch (((int) (user_data))) {
      case STYLE_SET:
        settingsChanged = true;
        break;
      case PROPERTY_NOTIFY:
        GdkEventProperty gdkEvent = new GdkEventProperty();
        OS.memmove(gdkEvent, arg1);
        if (gdkEvent.type == OS.GDK_PROPERTY_NOTIFY) {
          byte[] name = Converter.wcsToMbcs(null, "org.eclipse.swt.filePath.message", true);
          int atom = OS.gdk_x11_atom_to_xatom(OS.gdk_atom_intern(name, true));
          if (atom == OS.gdk_x11_atom_to_xatom(gdkEvent.atom)) {
            int xWindow = OS.gdk_x11_drawable_get_xid(OS.GTK_WIDGET_WINDOW(shellHandle));
            int[] type = new int[1];
            int[] format = new int[1];
            long[] nitems = new long[1];
            long[] bytes_after = new long[1];
            int[] data = new int[1];
            OS.XGetWindowProperty(
                OS.GDK_DISPLAY(),
                xWindow,
                atom,
                0L,
                -1L,
                true,
                AnyPropertyType,
                type,
                format,
                nitems,
                bytes_after,
                data);
            if (nitems[0] > 0) {
              byte[] buffer = new byte[((int) (nitems[0]))];
              OS.memmove(buffer, data[0], buffer.length);
              OS.XFree(data[0]);
              char[] chars = Converter.mbcsToWcs(null, buffer);
              String string = new String(chars);
              int lastIndex = 0;
              int index = string.indexOf(':');
              while (index != (-1)) {
                String file = string.substring(lastIndex, index);
                Event event = new Event();
                event.text = file;
                sendEvent(OpenDoc, event);
                lastIndex = index + 1;
                index = string.indexOf(':', lastIndex);
              }
            }
          }
        }
        break;
    }
    return 0;
  }
}
