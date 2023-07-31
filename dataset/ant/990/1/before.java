class PlaceHold {
  public int getAction() {
    String actionL = getValue().toLowerCase(Locale.US);
    if (actionL.equals("send") || actionL.equals("put")) {
      return SEND_FILES;
    } else if (actionL.equals("recv") || actionL.equals("get")) {
      return GET_FILES;
    } else if (actionL.equals("del") || actionL.equals("delete")) {
      return DEL_FILES;
    } else if (actionL.equals("list")) {
      return LIST_FILES;
    } else if (actionL.equals("chmod")) {
      return CHMOD;
    } else if (actionL.equals("mkdir")) {
      return MK_DIR;
    }
    return SEND_FILES;
  }
}
