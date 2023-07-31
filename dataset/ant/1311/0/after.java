class PlaceHold {
  public boolean isSelected(File basedir, String filename, File file) {
    validate();
    String[] destfiles = map.mapFileName(filename);
    if (destfiles == null) {
      return false;
    }
    if ((destfiles.length != 1) || (destfiles[0] == null)) {
      throw new BuildException(
          (("Invalid destination file results for " + targetdir.getName()) + " with filename ")
              + filename);
    }
    String destname = destfiles[0];
    File destfile = FILE_UTILS.resolveFile(targetdir, destname);
    boolean selected = selectionTest(file, destfile);
    return selected;
  }
}
