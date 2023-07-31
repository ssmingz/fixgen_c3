class PlaceHold {
  private boolean checkClass(String classname) {
    try {
      Class.forName(classname);
      return true;
    } catch (Throwable t) {
      project.log(t.toString(), "available", MSG_VERBOSE);
      return false;
    }
  }
}
