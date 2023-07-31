class PlaceHold {
  String createJavaString(int ptr) {
    int charArray = OS.String_ToCharArray(ptr);
    char[] chars = new char[OS.String_Length(ptr)];
    OS.memmove(chars, charArray, chars.length * 2);
    return new String(chars);
  }
}
