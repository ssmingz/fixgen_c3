class PlaceHold {
  public String getText(int start, int end) {
    checkWidget();
    if (txnObject == 0) {
      return new String(getEditText(start, end));
    } else {
      if (!((start <= end) && (0 <= end))) {
        return "";
      }
      int length = OS.TXNDataSize(txnObject) / 2;
      end = Math.min(end, length - 1);
      if (start > end) {
        return "";
      }
      start = Math.max(0, start);
      return getTXNText(start, end + 1);
    }
  }
}
