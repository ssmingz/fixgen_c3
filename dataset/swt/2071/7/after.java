class PlaceHold {
  int Read(int aBuf, int aCount, int _retval) {
    int max = Math.min(((int) (aCount)), buffer == null ? 0 : buffer.length - index);
    if (max > 0) {
      byte[] src = new byte[max];
      System.arraycopy(buffer, index, src, 0, max);
      XPCOM.memmove(aBuf, src, max);
      index += max;
    }
    XPCOM.memmove(_retval, new int[] {max}, 4);
    return XPCOM.NS_OK;
  }
}
