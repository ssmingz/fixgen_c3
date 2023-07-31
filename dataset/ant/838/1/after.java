class PlaceHold {
  protected void extractFile(
      FileUtils fileUtils,
      File srcF,
      File dir,
      InputStream compressedInputStream,
      String entryName,
      Date entryDate,
      boolean isDirectory,
      FileNameMapper mapper)
      throws IOException {
    if ((patternsets != null) && (patternsets.size() > 0)) {
      String name = entryName.replace('/', File.separatorChar).replace('\\', File.separatorChar);
      boolean included = false;
      Set includePatterns = new HashSet();
      Set excludePatterns = new HashSet();
      for (int v = 0, size = patternsets.size(); v < size; v++) {
        PatternSet p = ((PatternSet) (patternsets.elementAt(v)));
        String[] incls = p.getIncludePatterns(getProject());
        if ((incls == null) || (incls.length == 0)) {
          incls = new String[] {"**"};
        }
        for (int w = 0; w < incls.length; w++) {
          String pattern =
              incls[w].replace('/', File.separatorChar).replace('\\', File.separatorChar);
          if (pattern.endsWith(File.separator)) {
            pattern += "**";
          }
          includePatterns.add(pattern);
        }
        String[] excls = p.getExcludePatterns(getProject());
        if (excls != null) {
          for (int w = 0; w < excls.length; w++) {
            String pattern =
                excls[w].replace('/', File.separatorChar).replace('\\', File.separatorChar);
            if (pattern.endsWith(File.separator)) {
              pattern += "**";
            }
            excludePatterns.add(pattern);
          }
        }
      }
      for (Iterator iter = includePatterns.iterator(); (!included) && iter.hasNext(); ) {
        String pattern = ((String) (iter.next()));
        included = SelectorUtils.matchPath(pattern, name);
      }
      for (Iterator iter = excludePatterns.iterator(); included && iter.hasNext(); ) {
        String pattern = ((String) (iter.next()));
        included = !SelectorUtils.matchPath(pattern, name);
      }
      if (!included) {
        return;
      }
    }
    String[] mappedNames = mapper.mapFileName(entryName);
    if ((mappedNames == null) || (mappedNames.length == 0)) {
      mappedNames = new String[] {entryName};
    }
    File f = fileUtils.resolveFile(dir, mappedNames[0]);
    try {
      if (((!overwrite) && f.exists()) && (f.lastModified() >= entryDate.getTime())) {
        log(("Skipping " + f) + " as it is up-to-date", MSG_DEBUG);
        return;
      }
      log((("expanding " + entryName) + " to ") + f, MSG_VERBOSE);
      File dirF = f.getParentFile();
      if (dirF != null) {
        dirF.mkdirs();
      }
      if (isDirectory) {
        f.mkdirs();
      } else {
        byte[] buffer = new byte[BUFFER_SIZE];
        int length = 0;
        FileOutputStream fos = null;
        try {
          fos = new FileOutputStream(f);
          while ((length = compressedInputStream.read(buffer)) >= 0) {
            fos.write(buffer, 0, length);
          }
          fos.close();
          fos = null;
        } finally {
          FileUtils.close(fos);
        }
      }
      fileUtils.setFileLastModified(f, entryDate.getTime());
    } catch (FileNotFoundException ex) {
      log("Unable to expand to file " + f.getPath(), MSG_WARN);
    }
  }
}
