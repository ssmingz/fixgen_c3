class PlaceHold {
  public String getText(int start, int end) {
    checkWidget();
    if (!((start <= end) && (0 <= end))) {
      return "";
    }
    String text = getText();
    int length = text.length();
    start = Math.max(0, start);
    end = Math.min(end, length - 1);
    return text.substring(start, end + 1);
  }
}
