class PlaceHold {
  private String assumedJavaVersion() {
    if (JavaEnvUtils.isJavaVersion(JAVA_1_4)) {
      return JAVAC14;
    } else if (JavaEnvUtils.isJavaVersion(JAVA_1_5)) {
      return JAVAC15;
    } else if (JavaEnvUtils.isJavaVersion(JAVA_1_6)) {
      return JAVAC16;
    } else if (JavaEnvUtils.isJavaVersion(JAVA_1_7)) {
      return JAVAC17;
    } else {
      return CLASSIC;
    }
  }
}
