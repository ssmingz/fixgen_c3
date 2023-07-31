class PlaceHold {
  static String createJavaString(int ptr) {
    int charArray = OS.String_ToCharArray(ptr);
    char[] chars = new char[OS.String_Length(ptr)];
    OS.memcpy(chars, charArray, chars.length * 2);
    OS.GCHandle_Free(charArray);
    return new String(chars);
  }
}
