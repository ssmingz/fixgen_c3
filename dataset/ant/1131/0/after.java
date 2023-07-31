class PlaceHold {
  public void test3() {
    expectLog(
        "test3",
        (((((((((((("Current Default Excludes:\n" + "  **/*~\n") + "  **/#*#\n") + "  **/.#*\n")
                                                + "  **/%*%\n")
                                            + "  **/._*\n")
                                        + "  **/CVS/**\n")
                                    + "  **/.cvsignore\n")
                                + "  **/SCCS\n")
                            + "  **/SCCS/**\n")
                        + "  **/vssver.scc\n")
                    + "  **/.svn\n")
                + "  **/.svn/**\n")
            + "  **/.DS_Store");
  }
}
