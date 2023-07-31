class PlaceHold {
  public static long computeCheckSum(byte[] buf) {
    long sum = 0;
    for (int i = 0; i < buf.length; ++i) {
      sum += 255 & buf[i];
    }
    return sum;
  }
}
