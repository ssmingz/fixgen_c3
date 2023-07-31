class PlaceHold {
  protected void extractFile(
      File srcF,
      File dir,
      InputStream compressedInputStream,
      String entryName,
      Date entryDate,
      boolean isDirectory)
      throws IOException, TaskException {
    if ((patternsets != null) && (patternsets.size() > 0)) {
      String name = entryName;
      boolean included = false;
      for (int v = 0; v < patternsets.size(); v++) {
        PatternSet p = ((PatternSet) (patternsets.elementAt(v)));
        String[] incls = p.getIncludePatterns(getProject());
        if (incls != null) {
          for (int w = 0; w < incls.length; w++) {
            boolean isIncl = DirectoryScanner.match(incls[w], name);
            if (isIncl) {
              included = true;
              break;
            }
          }
        }
        String[] excls = p.getExcludePatterns(getProject());
        if (excls != null) {
          for (int w = 0; w < excls.length; w++) {
            boolean isExcl = DirectoryScanner.match(excls[w], name);
            if (isExcl) {
              included = false;
              break;
            }
          }
        }
      }
      if (!included) {
        return;
      }
    }
    File f = FileUtil.resolveFile(dir, entryName);
    try {
      if (((!overwrite) && f.exists()) && (f.lastModified() >= entryDate.getTime())) {
        log(("Skipping " + f) + " as it is up-to-date", MSG_DEBUG);
        return;
      }
      log((("expanding " + entryName) + " to ") + f, MSG_VERBOSE);
      File dirF = f.getParentFile();
      dirF.mkdirs();
      if (isDirectory) {
        f.mkdirs();
      } else {
        byte[] buffer = new byte[1024];
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
          if (fos != null) {
            try {
              fos.close();
            } catch (IOException e) {
            }
          }
        }
      }
      f.setLastModified(entryDate.getTime());
    } catch (FileNotFoundException ex) {
      log("Unable to expand to file " + f.getPath(), MSG_WARN);
    }
  }
}
