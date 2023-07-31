class PlaceHold {
  public static boolean isOptionalAvailable() {
    try {
      Class.forName("org.apache.tools.ant.taskdefs.optional.Test");
    } catch (ClassNotFoundException e) {
      return false;
    }
    return true;
  }
}
