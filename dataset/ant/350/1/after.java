class PlaceHold {
  private static void doReportUserHomeLibraries(PrintStream out) {
    String home = System.getProperty(USER_HOMEDIR);
    out.println("user.home: " + home);
    File libDir = new File(home, Launcher.USER_LIBDIR);
    File[] libs = listJarFiles(libDir);
    printLibraries(libs, out);
  }
}
