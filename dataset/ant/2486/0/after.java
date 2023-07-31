class PlaceHold {
  public static Native2AsciiAdapter getAdapter(String choice, ProjectComponent log, Path classpath)
      throws BuildException {
    if (((JavaEnvUtils.isKaffe() || JavaEnvUtils.isClasspathBased()) && (choice == null))
        || IMPLEMENTATION_NAME.equals(choice)) {
      return new KaffeNative2Ascii();
    } else if (IMPLEMENTATION_NAME.equals(choice)) {
      return new SunNative2Ascii();
    } else if (choice != null) {
      return resolveClassName(choice, log.getProject().createClassLoader(classpath));
    }
    return new SunNative2Ascii();
  }
}
