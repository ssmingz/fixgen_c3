class PlaceHold {
  protected void nextLine() throws BuildException {
    int ch = -1;
    int eolcount = 0;
    eolStr.setLength(0);
    line.setLength(0);
    try {
      ch = reader.read();
      while (((ch != (-1)) && (ch != '\r')) && (ch != '\n')) {
        line.append(((char) (ch)));
        ch = reader.read();
      }
      if ((ch == (-1)) && (line.length() == 0)) {
        reachedEof = true;
        return;
      }
      switch (((char) (ch))) {
        case '\r':
          ++eolcount;
          eolStr.append('\r');
          switch (((char) (ch = reader.read()))) {
            case '\r':
              if (((char) (ch = reader.read())) == '\n') {
                eolcount += 2;
                eolStr.append("\r\n");
              }
              break;
            case '\n':
              ++eolcount;
              eolStr.append('\n');
              break;
          }
          break;
        case '\n':
          ++eolcount;
          eolStr.append('\n');
          break;
      }
      if (eolcount == 0) {
        int i = line.length();
        while (((--i) >= 0) && (line.charAt(i) == CTRLZ)) {}
        if (i < (line.length() - 1)) {
          eofStr.append(line.toString().substring(i + 1));
          if (i < 0) {
            line.setLength(0);
            reachedEof = true;
          } else {
            line.setLength(i + 1);
          }
        }
      }
    } catch (IOException e) {
      throw new BuildException(e);
    }
  }
}
