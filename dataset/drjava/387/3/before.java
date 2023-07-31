class PlaceHold {
  public static Process runJVM(
      String mainClass, String[] classParams, String[] classPath, String[] jvmParams, File workDir)
      throws IOException {
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < classPath.length; i++) {
      if (i != 0) {
        buf.append(PATH_SEPARATOR);
      }
      buf.append(classPath[i]);
    }
    return runJVM(mainClass, classParams, buf.toString(), jvmParams, workDir);
  }
}
