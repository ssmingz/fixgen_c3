class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    if ((!checkHTML(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    String string = ((String) (object));
    int count = string.length();
    char[] chars = new char[count];
    string.getChars(0, count, chars, 0);
    byte[] buffer = new byte[chars.length * 2];
    OS.memmove(buffer, chars, buffer.length);
    transferData.data = new byte[1][];
    transferData.data[0] = buffer;
    transferData.result = OS.noErr;
  }
}
