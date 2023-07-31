class PlaceHold {
  protected void touch() throws BuildException {
    long defaultTimestamp = getTimestamp();
    if (file != null) {
      touch(new FileResource(file.getParentFile(), file.getName()), defaultTimestamp);
    }
    Iterator iter = resources.iterator();
    while (iter.hasNext()) {
      Resource r = ((Resource) (iter.next()));
      if (!(r instanceof Touchable)) {
        throw new BuildException("Can't touch " + r);
      }
      touch(r, defaultTimestamp);
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(getProject());
      File fromDir = fs.getDir(getProject());
      String[] srcDirs = ds.getIncludedDirectories();
      for (int j = 0; j < srcDirs.length; j++) {
        touch(new FileResource(fromDir, srcDirs[j]), defaultTimestamp);
      }
    }
  }
}
