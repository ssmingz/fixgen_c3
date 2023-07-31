class PlaceHold {
  private void readPatterns(File patternfile, Vector patternlist) {
    try {
      BufferedReader patternReader = new BufferedReader(new FileReader(patternfile));
      String line = patternReader.readLine();
      while (line != null) {
        if (line.length() > 0) {
          addPatternToList(patternlist).setName(line);
        }
        line = patternReader.readLine();
      }
    } catch (IOException ioe) {
      log("An error occured while reading from pattern file: " + patternfile, MSG_ERR);
    }
  }
}
