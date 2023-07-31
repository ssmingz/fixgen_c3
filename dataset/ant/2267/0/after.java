class PlaceHold {
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
    project.executeTarget("test2");
    assertEquals("current default excludes", expected, DirectoryScanner.getDefaultExcludes());
  }
}
