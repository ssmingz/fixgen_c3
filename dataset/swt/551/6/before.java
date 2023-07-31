class PlaceHold {
  static String kde_convertQStringAndFree(int qString) {
    int qCString = KDE.QString_utf8(qString);
    int charString = KDE.QCString_data(qCString);
    int length = OS.strlen(charString);
    byte[] buffer = new byte[length];
    OS.memmove(buffer, charString, length);
    String answer = new String(Converter.mbcsToWcs(null, buffer));
    KDE.QCString_delete(qCString);
    KDE.QString_delete(qString);
    return answer;
  }
}
