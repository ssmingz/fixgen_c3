class PlaceHold {
  public int getTopPixel() {
    checkWidget();
    int[] buffer = new int[2];
    long code = OS.SendMessage(handle, EM_GETSCROLLPOS, 0, buffer);
    if (code == 1) {
      return buffer[1];
    }
    return getTopIndex() * getLineHeight();
  }
}
