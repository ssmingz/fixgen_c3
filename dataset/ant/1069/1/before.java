class PlaceHold {
  protected void fillMapsFromArchive(
      Resource src,
      String encoding,
      Map fileEntries,
      Map matchFileEntries,
      Map dirEntries,
      Map matchDirEntries) {
    TarEntry entry = null;
    TarInputStream ti = null;
    try {
      try {
        ti = new TarInputStream(src.getInputStream());
      } catch (IOException ex) {
        throw new BuildException("problem opening " + srcFile, ex);
      }
      while ((entry = ti.getNextEntry()) != null) {
        Resource r = new TarResource(src, entry);
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
    } catch (IOException ex) {
      throw new BuildException("problem reading " + srcFile, ex);
    } finally {
      if (ti != null) {
        try {
          ti.close();
        } catch (IOException ex) {
        }
      }
    }
  }
}
