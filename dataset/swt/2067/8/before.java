class PlaceHold {
  String getLastErrorText() {
    int error = OS.GetLastError();
    if (error == 0) {
      return "";
    }
    int[] buffer = new int[1];
    int dwFlags =
        (OS.FORMAT_MESSAGE_ALLOCATE_BUFFER | OS.FORMAT_MESSAGE_FROM_SYSTEM)
            | OS.FORMAT_MESSAGE_IGNORE_INSERTS;
    int length = OS.FormatMessage(dwFlags, 0, error, LANG_USER_DEFAULT, buffer, 0, 0);
    if (length == 0) {
      return (" [GetLastError=0x" + Integer.toHexString(error)) + "]";
    }
    TCHAR buffer1 = new TCHAR(0, length);
    OS.MoveMemory(buffer1, buffer[0], length * TCHAR.sizeof);
    if (buffer[0] != 0) {
      OS.LocalFree(buffer[0]);
    }
    return buffer1.toString(0, length);
  }
}
