class PlaceHold {
  static boolean gnome_24_launch(String fileName) {
    byte[] fileNameBuffer = Converter.wcsToMbcs(null, fileName, true);
    int uri =
        GNOME.gnome_vfs_make_uri_from_input_with_dirs(
            fileNameBuffer, GNOME_VFS_MAKE_URI_DIR_CURRENT);
    int result = GNOME.gnome_vfs_url_show(uri);
    OS.g_free(uri);
    return result == GNOME.GNOME_VFS_OK;
  }
}
