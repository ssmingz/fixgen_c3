class PlaceHold {
  public static String getJdkExecutable(String command) {
    if (isNetware) {
      return command;
    }
    File jExecutable = null;
    if (isAix) {
      jExecutable = findInDir(javaHome + "/../sh", command);
    }
    if (jExecutable == null) {
      jExecutable = findInDir(javaHome + "/../bin", command);
    }
    if (jExecutable != null) {
      return jExecutable.getAbsolutePath();
    } else {
      return getJreExecutable(command);
    }
  }
}
