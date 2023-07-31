class PlaceHold {
  int controlKey(int key) {
    int upper = OS.CharUpper(((short) (key)));
    if ((64 <= upper) && (upper <= 95)) {
      return upper & 0xbf;
    }
    return key;
  }
}
