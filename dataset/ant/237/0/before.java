class PlaceHold {
  public void testSelectionBehaviour() {
    PresentSelector s;
    String results;
    Mapper m;
    Mapper.MapperType identity = new Mapper.MapperType();
    identity.setValue("identity");
    Mapper.MapperType glob = new Mapper.MapperType();
    glob.setValue("glob");
    Mapper.MapperType merge = new Mapper.MapperType();
    merge.setValue("merge");
    Mapper.MapperType flatten = new Mapper.MapperType();
    flatten.setValue("flatten");
    try {
      makeBed();
      s = ((PresentSelector) (getInstance()));
      s.setTargetdir(beddir);
      results = selectionString(s);
      assertEquals("TTTTTTTTTTTT", results);
      s = ((PresentSelector) (getInstance()));
      s.setTargetdir(beddir);
      m = s.createMapper();
      m.setType(identity);
      results = selectionString(s);
      assertEquals("TTTTTTTTTTTT", results);
      s = ((PresentSelector) (getInstance()));
      File subdir = new File("src/etc/testcases/taskdefs/expected");
      s.setTargetdir(subdir);
      m = s.createMapper();
      m.setType(flatten);
      results = selectionString(s);
      if (JavaEnvUtils.isJavaVersion(JAVA_1_1)) {
        assertEquals("TTTTTFFFFFFF", results);
      } else {
        assertEquals("TTTTTTTTTTTF", results);
      }
      s = ((PresentSelector) (getInstance()));
      s.setTargetdir(beddir);
      m = s.createMapper();
      m.setType(merge);
      m.setTo("asf-logo.gif.gz");
      results = selectionString(s);
      assertEquals("TTTTTTTTTTTT", results);
      s = ((PresentSelector) (getInstance()));
      subdir = new File(beddir, "tar/bz2");
      s.setTargetdir(subdir);
      m = s.createMapper();
      m.setType(glob);
      m.setFrom("*.bz2");
      m.setTo("*.tar.bz2");
      results = selectionString(s);
      assertEquals("FFTFFFFFFFFF", results);
      try {
        makeMirror();
        s = ((PresentSelector) (getInstance()));
        subdir = getProject().resolveFile("selectortest2");
        s.setTargetdir(subdir);
        results = mirrorSelectionString(s);
        assertEquals("TTTFFTTTTTTT", results);
        results = selectionString(s);
        assertEquals("TTTFFTTTTTTT", results);
      } finally {
        cleanupMirror();
      }
    } finally {
      cleanupBed();
    }
  }
}
