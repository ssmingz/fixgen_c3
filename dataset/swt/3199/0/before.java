class PlaceHold {
  public String toString() {
    if (handle == 0) {
      return null;
    }
    int length = XPCOM.nsEmbedString_Length(handle);
    int buffer = XPCOM.nsEmbedString_get(handle);
    char[] dest = new char[length];
    XPCOM.memmove(dest, buffer, length * 2);
    return new String(dest);
  }
}
