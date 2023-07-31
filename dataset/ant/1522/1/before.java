class PlaceHold {
  public void test1() {
    String[] expected =
        new String[] {
          "**/*~",
          "**/#*#",
          "**/.#*",
          "**/%*%",
          "**/._*",
          "**/CVS",
          "**/CVS/**",
          "**/.cvsignore",
          "**/SCCS",
          "**/SCCS/**",
          "**/vssver.scc",
          "**/.svn",
          "**/.svn/**",
          "**/.DS_Store"
        };
    project.executeTarget("test1");
    assertEquals("current default excludes", expected, DirectoryScanner.getDefaultExcludes());
  }
}
