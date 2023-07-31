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
      start = Math.max(0, start);
      end = Math.min(end, length - 1);
      return getTXNText(start, end + 1);
    }
  }
}
