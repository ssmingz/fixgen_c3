class PlaceHold {
  private void writePropertyFile(Properties properties, File dir) throws BuildException {
    BufferedOutputStream bos = null;
    try {
      bos = new BufferedOutputStream(new FileOutputStream(new File(dir, linkFileName)));
      properties.store(bos, "Symlinks from " + dir);
    } catch (IOException ioe) {
      throw new BuildException(ioe, getLocation());
    } finally {
      FILE_UTILS.close(bos);
    }
  }
}
