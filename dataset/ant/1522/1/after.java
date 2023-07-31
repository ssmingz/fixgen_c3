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
    project.executeTarget("test1");
    assertEquals("current default excludes", expected, DirectoryScanner.getDefaultExcludes());
  }
}
