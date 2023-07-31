class PlaceHold {
  private String assumedJavaVersion() {
    if (JavaEnvUtils.isJavaVersion(JAVA_1_1)) {
      return JAVAC11;
    } else if (JavaEnvUtils.isJavaVersion(JAVA_1_2)) {
      return JAVAC12;
    } else if (JavaEnvUtils.isJavaVersion(JAVA_1_3)) {
      return JAVAC13;
    } else if (JavaEnvUtils.isJavaVersion(JAVA_1_4)) {
      return JAVAC14;
    } else if (JavaEnvUtils.isJavaVersion(JAVA_1_5)) {
      return JAVAC15;
    } else if (JavaEnvUtils.isJavaVersion(JAVA_1_6)) {
      return JAVAC16;
    } else {
      return CLASSIC;
    }
  }
}
