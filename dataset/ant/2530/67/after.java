class PlaceHold {
  private void translate() throws TaskException {
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.get(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(getProject());
      String[] srcFiles = ds.getIncludedFiles();
      for (int j = 0; j < srcFiles.length; j++) {
        try {
          File dest = FileUtil.resolveFile(toDir, srcFiles[j]);
          try {
            File destDir = new File(dest.getParent());
            if (!destDir.exists()) {
              destDir.mkdirs();
            }
          } catch (Exception e) {
            log(
                ("Exception occured while trying to check/create " + " parent directory.  ")
                    + e.getMessage(),
                MSG_DEBUG);
          }
          destLastModified = dest.lastModified();
          srcLastModified = new File(srcFiles[i]).lastModified();
          if ((((((((forceOverwrite || (destLastModified < srcLastModified))
                                      || (destLastModified < bundleLastModified[0]))
                                  || (destLastModified < bundleLastModified[1]))
                              || (destLastModified < bundleLastModified[2]))
                          || (destLastModified < bundleLastModified[3]))
                      || (destLastModified < bundleLastModified[4]))
                  || (destLastModified < bundleLastModified[5]))
              || (destLastModified < bundleLastModified[6])) {
            log("Processing " + srcFiles[j], MSG_DEBUG);
            FileOutputStream fos = new FileOutputStream(dest);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos, destEncoding));
            FileInputStream fis = new FileInputStream(srcFiles[j]);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis, srcEncoding));
            String line;
            while ((line = in.readLine()) != null) {
              StringBuffer newline = new StringBuffer(line);
              int startIndex = -1;
              int endIndex = -1;
              outer:
              while (true) {
                startIndex = line.indexOf(startToken, endIndex + 1);
                if ((startIndex < 0) || ((startIndex + 1) >= line.length())) {
                  break;
                }
                endIndex = line.indexOf(endToken, startIndex + 1);
                if (endIndex < 0) {
                  break;
                }
                String matches = line.substring(startIndex + 1, endIndex);
                for (int k = 0; k < matches.length(); k++) {
                  char c = matches.charAt(k);
                  if (((c == ':') || (c == '=')) || Character.isSpaceChar(c)) {
                    endIndex = endIndex - 1;
                    continue outer;
                  }
                }
                String replace = null;
                replace = ((String) (resourceMap.get(matches)));
                if (replace == null) {
                  log(("Warning: The key: " + matches) + " hasn't been defined.", MSG_DEBUG);
                  replace = matches;
                }
                line = (line.substring(0, startIndex) + replace) + line.substring(endIndex + 1);
                endIndex = (startIndex + replace.length()) + 1;
                if ((endIndex + 1) >= line.length()) {
                  break;
                }
              }
              out.write(line);
              out.newLine();
            }
            if (in != null) {
              in.close();
            }
            if (out != null) {
              out.close();
            }
          } else {
            log(("Skipping " + srcFiles[j]) + " as destination file is up to date", MSG_VERBOSE);
          }
        } catch (IOException ioe) {
          throw new TaskException(ioe.getMessage());
        }
      }
    }
  }
}
