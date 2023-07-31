class PlaceHold {
  private static void doReportUserHomeLibraries(PrintStream out) {
    String home = System.getProperty(USER_HOMEDIR);
    out.println("user.home: " + home);
    File libDir =
        new File(home, (Launcher.ANT_PRIVATEDIR + File.separator) + Launcher.ANT_PRIVATELIB);
    File[] libs = listJarFiles(libDir);
    printLibraries(libs, out);
  }
}
