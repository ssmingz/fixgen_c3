class PlaceHold {
  static Program gnome_getProgram(Display display, String mimeType) {
    Program program = null;
    byte[] mimeTypeBuffer = Converter.wcsToMbcs(null, mimeType, true);
    int ptr = GNOME.gnome_vfs_mime_get_default_application(mimeTypeBuffer);
    if (ptr != 0) {
      program = new Program();
      program.display = display;
      program.name = mimeType;
      GnomeVFSMimeApplication application = new GnomeVFSMimeApplication();
      GNOME.memmove(application, ptr, sizeof);
      if (application.command != 0) {
        int length = OS.strlen(application.command);
        if (length > 0) {
          byte[] buffer = new byte[length];
          OS.memmove(buffer, application.command, length);
          program.command = new String(Converter.mbcsToWcs(null, buffer));
        }
      }
      program.gnomeExpectUri =
          application.expects_uris == GNOME.GNOME_VFS_MIME_APPLICATION_ARGUMENT_TYPE_URIS;
      int length = OS.strlen(application.id);
      byte[] buffer = new byte[length + 1];
      OS.memmove(buffer, application.id, length);
      LONG gnomeIconTheme = ((LONG) (display.getData(ICON_THEME_DATA)));
      int icon_name =
          GNOME.gnome_icon_lookup(
              gnomeIconTheme.value,
              0,
              null,
              buffer,
              0,
              mimeTypeBuffer,
              GNOME_ICON_LOOKUP_FLAGS_NONE,
              null);
      int path = 0;
      if (icon_name != 0) {
        path =
            GNOME.gnome_icon_theme_lookup_icon(
                gnomeIconTheme.value, icon_name, PREFERRED_ICON_SIZE, null, null);
      }
      if (path != 0) {
        length = OS.strlen(path);
        if (length > 0) {
          buffer = new byte[length];
          OS.memmove(buffer, path, length);
          program.iconPath = new String(Converter.mbcsToWcs(null, buffer));
        }
        OS.g_free(path);
      }
      if (icon_name != 0) {
        OS.g_free(icon_name);
      }
      GNOME.gnome_vfs_mime_application_free(ptr);
    }
    return (program != null) && (program.command != null) ? program : null;
  }
}
