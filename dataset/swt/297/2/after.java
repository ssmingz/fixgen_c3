class PlaceHold {
  public Object nativeToJava(TransferData transferData) {
    if (((!isSupportedType(transferData)) || (transferData.pValue == 0))
        || (transferData.length <= 0)) {
      return null;
    }
    int length = transferData.length;
    byte[] temp = new byte[length];
    OS.memmove(temp, transferData.pValue, length);
    int[] files = new int[0];
    int offset = 0;
    for (int i = 0; i < (temp.length - 1); i++) {
      if ((temp[i] == '\r') && (temp[i + 1] == '\n')) {
        int size = i - offset;
        int file = OS.g_malloc(size + 1);
        byte[] fileBuffer = new byte[size + 1];
        System.arraycopy(temp, offset, fileBuffer, 0, size);
        OS.memmove(file, fileBuffer, size + 1);
        int[] newFiles = new int[files.length + 1];
        System.arraycopy(files, 0, newFiles, 0, files.length);
        newFiles[files.length] = file;
        files = newFiles;
        offset = i + 2;
      }
    }
    if (offset < (temp.length - 2)) {
      int size = temp.length - offset;
      int file = OS.g_malloc(size + 1);
      byte[] fileBuffer = new byte[size + 1];
      System.arraycopy(temp, offset, fileBuffer, 0, size);
      OS.memmove(file, fileBuffer, size + 1);
      int[] newFiles = new int[files.length + 1];
      System.arraycopy(files, 0, newFiles, 0, files.length);
      newFiles[files.length] = file;
      files = newFiles;
    }
    String[] fileNames = new String[0];
    for (int i = 0; i < files.length; i++) {
      int[] error = new int[1];
      int localePtr = OS.g_filename_from_uri(files[i], null, error);
      OS.g_free(files[i]);
      if ((error[0] != 0) || (localePtr == 0)) {
        continue;
      }
      int utf8Ptr = OS.g_locale_to_utf8(localePtr, -1, null, null, error);
      OS.g_free(localePtr);
      if ((error[0] != 0) || (utf8Ptr == 0)) {
        continue;
      }
      int[] items_written = new int[1];
      int utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
      OS.g_free(utf8Ptr);
      length = ((int) (items_written[0]));
      char[] buffer = new char[length];
      OS.memmove(buffer, utf16Ptr, length * 2);
      OS.g_free(utf16Ptr);
      String name = new String(buffer);
      String[] newFileNames = new String[fileNames.length + 1];
      System.arraycopy(fileNames, 0, newFileNames, 0, fileNames.length);
      newFileNames[fileNames.length] = name;
      fileNames = newFileNames;
    }
    if (fileNames.length == 0) {
      return null;
    }
    return fileNames;
  }
}
