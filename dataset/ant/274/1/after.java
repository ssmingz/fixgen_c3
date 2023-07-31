class PlaceHold {
  @Test
  public void test2() {
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
          "**/.DS_Store",
          "foo"
        };
    buildRule.executeTarget("test2");
    assertArrayContentsEquals(
        "current default excludes", expected, DirectoryScanner.getDefaultExcludes());
  }
}
