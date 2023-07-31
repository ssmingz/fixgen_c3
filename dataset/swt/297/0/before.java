class PlaceHold {
  String interpretOsAnswer(String osAnswer) {
    if (osAnswer == null) {
      return null;
    }
    int separatorIndex = osAnswer.lastIndexOf(SEPARATOR);
    if ((separatorIndex + 1) == osAnswer.length()) {
      return null;
    }
    String answer = fullPath = osAnswer;
    fileName = fullPath.substring(separatorIndex + 1);
    filterPath = fullPath.substring(0, separatorIndex);
    if ((style & SWT.MULTI) == 0) {
      fileNames = new String[] {fileName};
    } else {
      int namesPtr = OS.gtk_file_selection_get_selections(handle);
      int namesPtr1 = namesPtr;
      int[] namePtr = new int[1];
      OS.memmove(namePtr, namesPtr1, PTR_SIZEOF);
      int length = 0;
      while (namePtr[0] != 0) {
        length++;
        namesPtr1 += OS.PTR_SIZEOF;
        OS.memmove(namePtr, namesPtr1, PTR_SIZEOF);
      }
      fileNames = new String[length];
      namePtr = new int[length];
      OS.memmove(namePtr, namesPtr, length * OS.PTR_SIZEOF);
      for (int i = 0; i < length; i++) {
        int utf8Ptr = OS.g_filename_to_utf8(namePtr[i], -1, null, null, null);
        int[] items_written = new int[1];
        int utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
        char[] buffer = new char[items_written[0]];
        OS.memmove(buffer, utf16Ptr, items_written[0] * 2);
        String name = new String(buffer);
        fileNames[i] = name.substring(name.lastIndexOf(SEPARATOR) + 1);
        OS.g_free(utf16Ptr);
        OS.g_free(utf8Ptr);
      }
      OS.g_strfreev(namesPtr);
    }
    return answer;
  }
}
