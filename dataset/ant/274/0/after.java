class PlaceHold {
  @Test
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
          "**/.git",
          "**/.git/**",
          "**/.gitattributes",
          "**/.gitignore",
          "**/.gitmodules",
          "**/.hg",
          "**/.hg/**",
          "**/.hgignore",
          "**/.hgsub",
          "**/.hgsubstate",
          "**/.hgtags",
          "**/.bzr",
          "**/.bzr/**",
          "**/.bzrignore",
          "**/.DS_Store"
        };
    buildRule.executeTarget("test3");
    assertArrayContentsEquals(
        "current default excludes", expected, DirectoryScanner.getDefaultExcludes());
  }
}
