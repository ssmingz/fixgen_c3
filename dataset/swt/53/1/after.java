class PlaceHold {
  static boolean gnome_isExecutable(String fileName) {
    byte[] fileNameBuffer = Converter.wcsToMbcs(null, fileName, true);
    if (!GNOME.gnome_vfs_is_executable_command_string(fileNameBuffer)) {
      return false;
    }
    int uri = GNOME.gnome_vfs_make_uri_from_input(fileNameBuffer);
    int mimeType = GNOME.gnome_vfs_get_mime_type(uri);
    OS.g_free(uri);
    byte[] exeType = Converter.wcsToMbcs(null, "application/x-executable", true);
    boolean result =
        GNOME.gnome_vfs_mime_type_get_equivalence(mimeType, exeType)
            != GNOME.GNOME_VFS_MIME_UNRELATED;
    if (!result) {
      byte[] shellType = Converter.wcsToMbcs(null, "application/x-shellscript", true);
      result =
          GNOME.gnome_vfs_mime_type_get_equivalence(mimeType, shellType)
              == GNOME.GNOME_VFS_MIME_IDENTICAL;
    }
    return result;
  }
}
