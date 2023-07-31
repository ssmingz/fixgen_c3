class PlaceHold {
  void interpretOsAnswer(String osAnswer) {
    if (osAnswer == null) {
      return;
    }
    int separatorIndex = calculateLastSeparatorIndex(osAnswer);
    if ((separatorIndex + 1) == osAnswer.length()) {
      answer = null;
      return;
    }
    answer = fullPath = osAnswer;
    fileName = answer.substring(separatorIndex + 1);
    if ((style & SWT.MULTI) == 0) {
      fileNames = new String[] {fileName};
    } else {
      int namesPtr = OS.gtk_file_selection_get_selections(handle);
      int namesPtr1 = namesPtr;
      int[] namePtr = new int[1];
      OS.memmove(namePtr, namesPtr1, 1);
      int length = 0;
      while (namePtr[0] != 0) {
        length++;
        namesPtr1 += 4;
        OS.memmove(namePtr, namesPtr1, 1);
      }
      fileNames = new String[length];
      namePtr = new int[length];
      OS.memmove(namePtr, namesPtr, length * 4);
      for (int i = 0; i < length; i++) {
        int bytesPtr = OS.g_filename_to_utf8(namePtr[i], -1, 0, 0, 0);
        if (bytesPtr == 0) {
          continue;
        }
        byte[] bytes = new byte[OS.strlen(bytesPtr)];
        OS.memmove(bytes, bytesPtr, bytes.length);
        fileNames[i] = new String(Converter.mbcsToWcs("UTF8", bytes));
        OS.g_free(bytesPtr);
      }
      OS.g_strfreev(namesPtr);
    }
  }
}
