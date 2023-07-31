class PlaceHold {
  String computeResultChooserDialog() {
    if ((style & (SWT.SAVE | SWT.MULTI)) == SWT.MULTI) {
      int list = OS.gtk_file_chooser_get_filenames(handle);
      int listLength = OS.g_slist_length(list);
      fileNames = new String[listLength];
      int current = list;
      int writePos = 0;
      String path = null;
      for (int i = 0; i < listLength; i++) {
        int name = OS.g_slist_data(current);
        int utf8Ptr = OS.g_filename_to_utf8(name, -1, null, null, null);
        OS.g_free(name);
        if (utf8Ptr != 0) {
          int[] items_written = new int[1];
          int utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
          OS.g_free(utf8Ptr);
          if (utf16Ptr != 0) {
            int clength = ((int) (items_written[0]));
            char[] chars = new char[clength];
            OS.memmove(chars, utf16Ptr, clength * 2);
            OS.g_free(utf16Ptr);
            path = new String(chars);
            fileNames[writePos++] = path.substring(path.lastIndexOf(SEPARATOR) + 1);
          }
        }
        current = OS.g_slist_next(current);
      }
      if (writePos != listLength) {
        String[] validFileNames = new String[writePos];
        System.arraycopy(fileNames, 0, validFileNames, 0, writePos);
        fileNames = validFileNames;
      }
      OS.g_slist_free(list);
      fullPath = path;
    } else {
      int path = OS.gtk_file_chooser_get_filename(handle);
      if (path == 0) {
        return null;
      }
      int utf8Ptr = OS.g_filename_to_utf8(path, -1, null, null, null);
      OS.g_free(path);
      if (utf8Ptr == 0) {
        return null;
      }
      int[] items_written = new int[1];
      int utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
      OS.g_free(utf8Ptr);
      if (utf16Ptr == 0) {
        return null;
      }
      int clength = ((int) (items_written[0]));
      char[] chars = new char[clength];
      OS.memmove(chars, utf16Ptr, clength * 2);
      OS.g_free(utf16Ptr);
      fullPath = new String(chars);
      fileNames = new String[1];
      fileNames[0] = fullPath.substring(fullPath.lastIndexOf(SEPARATOR) + 1);
    }
    int separatorIndex = fullPath.lastIndexOf(SEPARATOR);
    fileName = fullPath.substring(separatorIndex + 1);
    filterPath = fullPath.substring(0, separatorIndex);
    return fullPath;
  }
}
