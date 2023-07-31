class PlaceHold {
  private void createFolders(File file) throws IOException, ParseException {
    BufferedReader in = null;
    try {
      in = new BufferedReader(new FileReader(file));
      MessageFormat mf = new MessageFormat(getFilenameFormat());
      String line = in.readLine();
      while (line != null) {
        log(("Considering \"" + line) + "\"", MSG_VERBOSE);
        if ((line.startsWith("\"\\") || line.startsWith("\"/"))
            || (((((line.length() > POS_3) && line.startsWith("\""))
                        && Character.isLetter(line.charAt(POS_1)))
                    && String.valueOf(line.charAt(POS_2)).equals(":"))
                && String.valueOf(line.charAt(POS_3)).equals("\\"))) {
          Object[] objs = mf.parse(line);
          String f = ((String) (objs[1]));
          int index = f.lastIndexOf(File.separator);
          if (index > (-1)) {
            File dir = new File(f.substring(0, index));
            if (!dir.exists()) {
              log("Creating " + dir.getAbsolutePath(), MSG_VERBOSE);
              if (dir.mkdirs()) {
                log("Created " + dir.getAbsolutePath(), MSG_INFO);
              } else {
                log("Failed to create " + dir.getAbsolutePath(), MSG_INFO);
              }
            } else {
              log(dir.getAbsolutePath() + " exists. Skipping", MSG_VERBOSE);
            }
          } else {
            log("File separator problem with " + line, MSG_WARN);
          }
        } else {
          log(("Skipped \"" + line) + "\"", MSG_VERBOSE);
        }
        line = in.readLine();
      }
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }
}
