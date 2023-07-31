class PlaceHold {
  public static byte[] getBytes(long value) {
    byte[] result = new byte[WORD];
    putLong(value, result, 0);
    return result;
  }
}
