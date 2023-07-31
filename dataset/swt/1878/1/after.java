class PlaceHold {
  static Program gio_getProgram(Display display, int application) {
    Program program = new Program();
    program.display = display;
    int length;
    byte[] buffer;
    int applicationName = OS.g_app_info_get_name(application);
    if (applicationName != 0) {
      length = OS.strlen(applicationName);
      if (length > 0) {
        buffer = new byte[length];
        OS.memmove(buffer, applicationName, length);
        program.name = new String(Converter.mbcsToWcs(null, buffer));
      }
    }
    int applicationCommand = OS.g_app_info_get_executable(application);
    if (applicationCommand != 0) {
      length = OS.strlen(applicationCommand);
      if (length > 0) {
        buffer = new byte[length];
        OS.memmove(buffer, applicationCommand, length);
        program.command = new String(Converter.mbcsToWcs(null, buffer));
      }
    }
    program.gnomeExpectUri = OS.g_app_info_supports_uris(application);
    int icon = OS.g_app_info_get_icon(application);
    if (icon != 0) {
      int icon_name = OS.g_icon_to_string(icon);
      if (icon_name != 0) {
        length = OS.strlen(icon_name);
        if (length > 0) {
          buffer = new byte[length];
          OS.memmove(buffer, icon_name, length);
          program.iconPath = new String(Converter.mbcsToWcs(null, buffer));
        }
        OS.g_free(icon_name);
      }
      OS.g_object_unref(icon);
    }
    return program.command != null ? program : null;
  }
}
