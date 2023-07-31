class PlaceHold {
  protected final Method getSetLastModified() {
    if (JavaEnvUtils.isJavaVersion(JAVA_1_1)) {
      return null;
    }
    if (setLastModified == null) {
      synchronized (lockReflection) {
        if (setLastModified == null) {
          try {
            setLastModified = File.class.getMethod("setLastModified", new Class[] {Long.TYPE});
          } catch (NoSuchMethodException nse) {
            throw new BuildException("File.setlastModified not in JDK > 1.1?", nse);
          }
        }
      }
    }
    return setLastModified;
  }
}
