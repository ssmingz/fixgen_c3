class PlaceHold {
  public static Path getCoberturaDefaultClasspath() {
    Path classpath = new Path(TestUtils.project);
    DirSet dirSetInstrumentDir = new DirSet();
    DirSet dirSetSrcDir = new DirSet();
    dirSetInstrumentDir.setDir(new File(getTempDir(), "instrument"));
    dirSetSrcDir.setDir(new File(getTempDir(), "src"));
    classpath.addDirset(dirSetInstrumentDir);
    classpath.addDirset(dirSetSrcDir);
    classpath.addDirset(TestUtils.getCoberturaClassDirSet());
    classpath.add(createDependencyPath("org.slf4j", "slf4j-api", "1.7.5"));
    return classpath;
  }
}
