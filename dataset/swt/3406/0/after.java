class PlaceHold {
  public int getTextLimit() {
    checkWidget();
    int limit = ((int) (OS.SendMessage(handle, EM_GETLIMITTEXT, 0, 0))) & 0x7fffffff;
    if ((segments != null) && (limit < LIMIT)) {
      limit = Math.max(1, limit - segments.length);
    }
    return limit;
  }
}
