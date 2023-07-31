class PlaceHold {
  public void test3() {
    String[] expected =
        new String[] {
          "**/*~",
          "**/#*#",
          "**/.#*",
          "**/%*%",
          "**/._*",
          "**/CVS/**",
          "**/.cvsignore",
          "**/SCCS",
          "**/SCCS/**",
          "**/vssver.scc",
          "**/.svn",
          "**/.svn/**",
          "**/.DS_Store"
        };
    project.executeTarget("test3");
    assertEquals("current default excludes", expected, DirectoryScanner.getDefaultExcludes());
  }
}
