class PlaceHold {
  static int unpackData(int[] handle, byte[] buffer, int offset) {
    int length =
        ((((buffer[offset++] & 0xff) << 0) | ((buffer[offset++] & 0xff) << 8))
                | ((buffer[offset++] & 0xff) << 16))
            | ((buffer[offset++] & 0xff) << 24);
    handle[0] = OS.NewHandle(length);
    if (handle[0] == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int[] ptr = new int[1];
    OS.HLock(handle[0]);
    OS.memmove(ptr, handle[0], 4);
    byte[] buffer1 = new byte[length];
    System.arraycopy(buffer, offset, buffer1, 0, length);
    OS.memmove(ptr[0], buffer1, length);
    OS.HUnlock(handle[0]);
    return offset + length;
  }
}
