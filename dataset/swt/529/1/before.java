class PlaceHold {
  String computeResultChooserDialog() {
    fullPath = null;
    if ((style & (SWT.SAVE | SWT.MULTI)) == SWT.MULTI) {
      int list = OS.gtk_file_chooser_get_filenames(handle);
      int listLength = OS.g_slist_length(list);
      fileNames = new String[listLength];
      int current = list;
      int writePos = 0;
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
            fullPath = new String(chars);
            fileNames[writePos++] = fullPath.substring(fullPath.lastIndexOf(SEPARATOR) + 1);
          }
        }
        current = OS.g_slist_next(current);
      }
      if ((writePos != 0) && (writePos != listLength)) {
        String[] validFileNames = new String[writePos];
        System.arraycopy(fileNames, 0, validFileNames, 0, writePos);
        fileNames = validFileNames;
      }
      OS.g_slist_free(list);
    } else {
      int path = OS.gtk_file_chooser_get_filename(handle);
      if (path != 0) {
        int utf8Ptr = OS.g_filename_to_utf8(path, -1, null, null, null);
        OS.g_free(path);
        if (utf8Ptr != 0) {
          int[] items_written = new int[1];
          int utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
          OS.g_free(utf8Ptr);
          if (utf16Ptr != 0) {
            int clength = ((int) (items_written[0]));
            char[] chars = new char[clength];
            OS.memmove(chars, utf16Ptr, clength * 2);
            OS.g_free(utf16Ptr);
            fullPath = new String(chars);
            fileNames = new String[1];
            fileNames[0] = fullPath.substring(fullPath.lastIndexOf(SEPARATOR) + 1);
          }
        }
      }
    }
    int filter = OS.gtk_file_chooser_get_filter(handle);
    if (filter != 0) {
      int filterNamePtr = OS.gtk_file_filter_get_name(filter);
      if (filterNamePtr != 0) {
        int length = OS.strlen(filterNamePtr);
        byte[] buffer = new byte[length];
        OS.memmove(buffer, filterNamePtr, length);
        String filterName = new String(Converter.mbcsToWcs(null, buffer));
        for (int i = 0; i < filterExtensions.length; i++) {
          if (filterNames.length > 0) {
            if (filterNames[i].equals(filterName)) {
              filterIndex = i;
              break;
            }
          } else if (filterExtensions[i].equals(filterName)) {
            filterIndex = i;
            break;
          }
        }
      }
    }
    if (fullPath != null) {
      int separatorIndex = fullPath.lastIndexOf(SEPARATOR);
      fileName = fullPath.substring(separatorIndex + 1);
      filterPath = fullPath.substring(0, separatorIndex);
    }
    return fullPath;
  }
}
