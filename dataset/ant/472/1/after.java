class PlaceHold {
  public void copyFile(
      File sourceFile,
      File destFile,
      FilterSetCollection filters,
      Vector filterChains,
      boolean overwrite,
      boolean preserveLastModified,
      String inputEncoding,
      String outputEncoding,
      Project project)
      throws IOException {
    if ((overwrite || (!destFile.exists()))
        || (destFile.lastModified() < sourceFile.lastModified())) {
      if (destFile.exists() && destFile.isFile()) {
        destFile.delete();
      }
      File parent = getParentFile(destFile);
      if ((parent != null) && (!parent.exists())) {
        parent.mkdirs();
      }
      final boolean filterSetsAvailable = (filters != null) && filters.hasFilters();
      final boolean filterChainsAvailable = (filterChains != null) && (filterChains.size() > 0);
      if (filterSetsAvailable) {
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
          if (inputEncoding == null) {
            in = new BufferedReader(new FileReader(sourceFile));
          } else {
            InputStreamReader isr =
                new InputStreamReader(new FileInputStream(sourceFile), inputEncoding);
            in = new BufferedReader(isr);
          }
          if (outputEncoding == null) {
            out = new BufferedWriter(new FileWriter(destFile));
          } else {
            OutputStreamWriter osw =
                new OutputStreamWriter(new FileOutputStream(destFile), outputEncoding);
            out = new BufferedWriter(osw);
          }
          if (filterChainsAvailable) {
            ChainReaderHelper crh = new ChainReaderHelper();
            crh.setBufferSize(8192);
            crh.setPrimaryReader(in);
            crh.setFilterChains(filterChains);
            crh.setProject(project);
            Reader rdr = crh.getAssembledReader();
            in = new BufferedReader(rdr);
          }
          LineTokenizer lineTokenizer = new LineTokenizer();
          lineTokenizer.setIncludeDelims(true);
          String newline = null;
          String line = lineTokenizer.getToken(in);
          while (line != null) {
            if (line.length() == 0) {
              out.newLine();
            } else {
              newline = filters.replaceTokens(line);
              out.write(newline);
            }
            line = lineTokenizer.getToken(in);
          }
        } finally {
          if (out != null) {
            out.close();
          }
          if (in != null) {
            in.close();
          }
        }
      } else if ((filterChainsAvailable
              || ((inputEncoding != null) && (!inputEncoding.equals(outputEncoding))))
          || ((inputEncoding == null) && (outputEncoding != null))) {
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
          if (inputEncoding == null) {
            in = new BufferedReader(new FileReader(sourceFile));
          } else {
            in =
                new BufferedReader(
                    new InputStreamReader(new FileInputStream(sourceFile), inputEncoding));
          }
          if (outputEncoding == null) {
            out = new BufferedWriter(new FileWriter(destFile));
          } else {
            out =
                new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(destFile), outputEncoding));
          }
          if (filterChainsAvailable) {
            ChainReaderHelper crh = new ChainReaderHelper();
            crh.setBufferSize(8192);
            crh.setPrimaryReader(in);
            crh.setFilterChains(filterChains);
            crh.setProject(project);
            Reader rdr = crh.getAssembledReader();
            in = new BufferedReader(rdr);
          }
          char[] buffer = new char[1024 * 8];
          while (true) {
            int nRead = in.read(buffer, 0, buffer.length);
            if (nRead == (-1)) {
              break;
            }
            out.write(buffer, 0, nRead);
          }
        } finally {
          if (out != null) {
            out.close();
          }
          if (in != null) {
            in.close();
          }
        }
      } else {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
          in = new FileInputStream(sourceFile);
          out = new FileOutputStream(destFile);
          byte[] buffer = new byte[8 * 1024];
          int count = 0;
          do {
            out.write(buffer, 0, count);
            count = in.read(buffer, 0, buffer.length);
          } while (count != (-1));
        } finally {
          if (out != null) {
            out.close();
          }
          if (in != null) {
            in.close();
          }
        }
      }
      if (preserveLastModified) {
        setFileLastModified(destFile, sourceFile.lastModified());
      }
    }
  }
}
