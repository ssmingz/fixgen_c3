class PlaceHold {
  static int packData(int handle, byte[] buffer, int offset) {
    int length = OS.GetHandleSize(handle);
    buffer[offset++] = ((byte) ((length & 0xff) >> 0));
    buffer[offset++] = ((byte) ((length & 0xff00) >> 8));
    buffer[offset++] = ((byte) ((length & 0xff0000) >> 16));
    buffer[offset++] = ((byte) ((length & 0xff000000) >> 24));
    int[] ptr = new int[1];
    OS.HLock(handle);
    OS.memcpy(ptr, handle, 4);
    byte[] buffer1 = new byte[length];
    OS.memcpy(buffer1, ptr[0], length);
    OS.HUnlock(handle);
    System.arraycopy(buffer1, 0, buffer, offset, length);
    return offset + length;
  }
}
