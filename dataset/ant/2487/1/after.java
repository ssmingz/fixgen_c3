class PlaceHold {
  private boolean checkClass(String classname) {
    try {
      Class.forName(classname);
      return true;
    } catch (Throwable t) {
      log(t.toString(), MSG_VERBOSE);
      return false;
    }
  }
}
