class PlaceHold {
  int ReadSegments(int aWriter, int aClosure, int aCount, int _retval) {
    int max = Math.min(((int) (aCount)), buffer == null ? 0 : buffer.length - index);
    int cnt = max;
    while (cnt > 0) {
      int[] aWriteCount = new int[1];
      int rc = XPCOM.Call(aWriter, getAddress(), aClosure, buffer, index, cnt, aWriteCount);
      if (rc != XPCOM.NS_OK) {
        break;
      }
      index += aWriteCount[0];
      cnt -= aWriteCount[0];
    }
    XPCOM.memmove(_retval, new int[] {max - cnt}, 4);
    return XPCOM.NS_OK;
  }
}
