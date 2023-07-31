class PlaceHold {
  public void testGetExecutableMostPlatforms() {
    if ((!Os.isName("netware")) && (!Os.isFamily("windows"))) {
      String javaHome = FILE_UTILS.normalize(System.getProperty("java.home")).getAbsolutePath();
      String extension = (Os.isFamily("dos")) ? ".exe" : "";
      String j = JavaEnvUtils.getJreExecutable("java");
      if (!extension.equals("")) {
        assertTrue(j.endsWith(extension));
      }
      assertTrue(j + " is absolute", new File(j).isAbsolute());
      assertTrue(j + " is normalized and in the JRE dir", j.startsWith(javaHome));
      j = JavaEnvUtils.getJdkExecutable("javac");
      if (!extension.equals("")) {
        assertTrue(j.endsWith(extension));
      }
      assertTrue(j + " is absolute", new File(j).isAbsolute());
      String javaHomeParent = FILE_UTILS.normalize(javaHome + "/..").getAbsolutePath();
      assertTrue(j + " is normalized and in the JDK dir", j.startsWith(javaHomeParent));
      if (Os.isFamily("mac") && (JavaEnvUtils.getJavaVersionNumber() <= JavaEnvUtils.VERSION_1_6)) {
        assertTrue(j + " is normalized and in the JRE dir", j.startsWith(javaHome));
      } else {
        assertTrue(j + " is normalized and not in the JRE dir", !j.startsWith(javaHome));
      }
      assertEquals("foo" + extension, JavaEnvUtils.getJreExecutable("foo"));
      assertEquals("foo" + extension, JavaEnvUtils.getJdkExecutable("foo"));
    }
  }
}
