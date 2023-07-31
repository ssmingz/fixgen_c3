class PlaceHold {
  private void processFile(File src) throws BuildException {
    if (!src.exists()) {
      throw new BuildException(
          ("Replace: source file " + src.getPath()) + " doesn't exist", location);
    }
    File temp = new File(src.getPath() + ".temp");
    if (temp.exists()) {
      throw new BuildException(
          ("Replace: temporary file " + temp.getPath()) + " already exists", location);
    }
    try {
      BufferedReader br = new BufferedReader(new FileReader(src));
      BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
      int fileLengthInBytes = ((int) (src.length()));
      StringBuffer tmpBuf = new StringBuffer(fileLengthInBytes);
      int readChar = 0;
      int totread = 0;
      while (true) {
        readChar = br.read();
        if (readChar < 0) {
          break;
        }
        tmpBuf.append(((char) (readChar)));
        totread++;
      }
      String buf = tmpBuf.toString();
      String newString = new String(buf);
      if (token != null) {
        String linesep = System.getProperty("line.separator");
        String val = stringReplace(value.getText(), "\n", linesep);
        String tok = stringReplace(token.getText(), "\n", linesep);
        log(
            (((("Replacing in " + src.getPath()) + ": ") + token.getText()) + " --> ")
                + value.getText(),
            MSG_VERBOSE);
        newString = stringReplace(newString, tok, val);
      }
      if (replacefilters.size() > 0) {
        newString = processReplacefilters(newString, src.getPath());
      }
      boolean changes = !newString.equals(buf);
      if (changes) {
        bw.write(newString, 0, newString.length());
        bw.flush();
      }
      bw.close();
      br.close();
      if (changes) {
        src.delete();
        temp.renameTo(src);
      } else {
        temp.delete();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
      throw new BuildException(ioe, location);
    }
  }
}
