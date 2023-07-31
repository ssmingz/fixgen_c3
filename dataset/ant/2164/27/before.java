class PlaceHold {
  public String[] list() throws TaskException {
    ArrayList result = new ArrayList(2 * m_elements.size());
    for (int i = 0; i < m_elements.size(); i++) {
      Object o = m_elements.get(i);
      if (o instanceof String) {
        addUnlessPresent(result, ((String) (o)));
      } else if (o instanceof PathElement) {
        final PathElement element = ((PathElement) (o));
        final String[] parts = element.getParts(m_baseDirectory);
        if (parts == null) {
          throw new NullPointerException("You must either set location or path on <pathelement>");
        }
        for (int j = 0; j < parts.length; j++) {
          addUnlessPresent(result, parts[j]);
        }
      } else if (o instanceof Path) {
        Path p = ((Path) (o));
        String[] parts = p.list();
        for (int j = 0; j < parts.length; j++) {
          addUnlessPresent(result, parts[j]);
        }
      } else if (o instanceof FileSet) {
        final FileSet fs = ((FileSet) (o));
        final DirectoryScanner ds = fs.getDirectoryScanner();
        final String[] s = ds.getIncludedFiles();
        final File dir = fs.getDir();
        for (int j = 0; j < s.length; j++) {
          File f = new File(dir, s[j]);
          String absolutePath = f.getAbsolutePath();
          addUnlessPresent(result, FileUtils.translateFile(absolutePath));
        }
      }
    }
    return ((String[]) (result.toArray(new String[result.size()])));
  }
}
