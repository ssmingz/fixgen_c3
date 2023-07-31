class PlaceHold {
  public static File getToolsJar() {
    boolean toolsJarAvailable = false;
    try {
      Class.forName("com.sun.tools.javac.Main");
      toolsJarAvailable = true;
    } catch (Exception e) {
      try {
        Class.forName("sun.tools.javac.Main");
        toolsJarAvailable = true;
      } catch (Exception e2) {
      }
    }
    if (toolsJarAvailable) {
      return null;
    }
    String libToolsJar = ((File.separator + "lib") + File.separator) + "tools.jar";
    String javaHome = System.getProperty("java.home");
    File toolsJar = new File(javaHome + libToolsJar);
    if (toolsJar.exists()) {
      return toolsJar;
    }
    if (javaHome.toLowerCase(Locale.ENGLISH).endsWith(File.separator + "jre")) {
      javaHome = javaHome.substring(0, javaHome.length() - "/jre".length());
      toolsJar = new File(javaHome + libToolsJar);
    }
    if (!toolsJar.exists()) {
      System.out.println(
          ("Unable to locate tools.jar. " + "Expected to find it in ") + toolsJar.getPath());
      return null;
    }
    return toolsJar;
  }
}
