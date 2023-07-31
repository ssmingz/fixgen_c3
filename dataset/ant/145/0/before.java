class PlaceHold {
  protected boolean scanDir(File srcDir, String[] files) {
    SourceFileScanner sfs = new SourceFileScanner(this);
    FileNameMapper mapper = null;
    File dir = srcDir;
    if (mapperElement == null) {
      MergingMapper mm = new MergingMapper();
      mm.setTo(_targetFile.getAbsolutePath());
      mapper = mm;
      dir = null;
    } else {
      mapper = mapperElement.getImplementation();
    }
    return sfs.restrict(files, srcDir, dir, mapper).length == 0;
  }
}
