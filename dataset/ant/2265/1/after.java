class PlaceHold {
  private void createIndexList(ZipOutputStream zOut) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(baos, "UTF8"));
    writer.println("JarIndex-Version: 1.0");
    writer.println();
    writer.println(zipFile.getName());
    writeIndexLikeList(new ArrayList(addedDirs.keySet()), rootEntries, writer);
    writer.println();
    if (indexJars != null) {
      Manifest mf = createManifest();
      Manifest.Attribute classpath = mf.getMainSection().getAttribute(ATTRIBUTE_CLASSPATH);
      String[] cpEntries = null;
      if ((classpath != null) && (classpath.getValue() != null)) {
        StringTokenizer tok = new StringTokenizer(classpath.getValue(), " ");
        cpEntries = new String[tok.countTokens()];
        int c = 0;
        while (tok.hasMoreTokens()) {
          cpEntries[c++] = tok.nextToken();
        }
      }
      String[] indexJarEntries = indexJars.list();
      for (int i = 0; i < indexJarEntries.length; i++) {
        String name = findJarName(indexJarEntries[i], cpEntries);
        if (name != null) {
          ArrayList dirs = new ArrayList();
          ArrayList files = new ArrayList();
          grabFilesAndDirs(indexJarEntries[i], dirs, files);
          if ((dirs.size() + files.size()) > 0) {
            writer.println(name);
            writeIndexLikeList(dirs, files, writer);
            writer.println();
          }
        }
      }
    }
    if (writer.checkError()) {
      throw new IOException("Encountered an error writing jar index");
    }
    writer.close();
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    try {
      super.zipFile(
          bais, zOut, INDEX_NAME, System.currentTimeMillis(), null, DEFAULT_FILE_MODE, null);
    } finally {
      FileUtils.close(bais);
    }
  }
}
