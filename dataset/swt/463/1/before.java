class PlaceHold {
  char mbcsToWcs(char ch) {
    int key = ch & 0xffff;
    if (key <= 0x7f) {
      return ch;
    }
    byte[] buffer;
    if (key <= 0xff) {
      buffer = new byte[1];
      buffer[0] = ((byte) (key));
    } else {
      buffer = new byte[2];
      buffer[0] = ((byte) ((key >> 8) & 0xff));
      buffer[1] = ((byte) (key & 0xff));
    }
    char[] result = Converter.mbcsToWcs(null, buffer);
    if (result.length == 0) {
      return 0;
    }
    return result[0];
  }
}
