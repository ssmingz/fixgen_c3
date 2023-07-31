class PlaceHold {
  private static String getTestClassPath() {
    String classpath = System.getProperty("build.tests");
    if (classpath == null) {
      System.err.println("WARNING: 'build.tests' property is not available !");
      classpath = System.getProperty("java.class.path");
    }
    if (JavaEnvUtils.isJavaVersion(JAVA_1_1)) {
      classpath +=
          ((((File.pathSeparator + System.getProperty("java.home")) + File.separator) + "lib")
                  + File.separator)
              + "classes.zip";
    }
    return classpath;
  }
}
