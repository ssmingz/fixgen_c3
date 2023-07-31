class PlaceHold {
  public String getText(int start, int end) {
    checkWidget();
    if (!((start <= end) && (0 <= end))) {
      return "";
    }
    String text = getText();
    int length = text.length();
    end = Math.min(end, length - 1);
    if (start > end) {
      return "";
    }
    start = Math.max(0, start);
    return text.substring(start, end + 1);
  }
}
