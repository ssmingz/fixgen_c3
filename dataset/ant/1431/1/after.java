class PlaceHold {
  protected void fillMapsFromArchive(
      Resource src,
      String encoding,
      Map fileEntries,
      Map matchFileEntries,
      Map dirEntries,
      Map matchDirEntries) {
    ZipEntry entry = null;
    ZipFile zf = null;
    File srcFile = null;
    if (src instanceof FileResource) {
      srcFile = ((FileResource) (src)).getFile();
    } else {
      throw new BuildException("only file resources are supported");
    }
    try {
      try {
        zf = new ZipFile(srcFile, encoding);
      } catch (ZipException ex) {
        throw new BuildException("problem reading " + srcFile, ex);
      } catch (IOException ex) {
        throw new BuildException("problem opening " + srcFile, ex);
      }
      Enumeration e = zf.getEntries();
      while (e.hasMoreElements()) {
        entry = ((ZipEntry) (e.nextElement()));
        Resource r = new ZipResource(srcFile, encoding, entry);
        String name = entry.getName();
        if (entry.isDirectory()) {
          name = trimSeparator(name);
          dirEntries.put(name, r);
          if (match(name)) {
            matchDirEntries.put(name, r);
          }
        } else {
          fileEntries.put(name, r);
          if (match(name)) {
            matchFileEntries.put(name, r);
          }
        }
      }
    } finally {
      ZipFile.closeQuietly(zf);
    }
  }
}
