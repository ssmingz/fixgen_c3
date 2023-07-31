class PlaceHold {
  private void processFile(File src) throws TaskException {
    if (!src.exists()) {
      throw new TaskException(("Replace: source file " + src.getPath()) + " doesn't exist");
    }
    File temp = null;
    try {
      temp = File.createTempFile("rep", ".tmp", src.getParentFile());
    } catch (IOException ioe) {
      throw new TaskException(ioe.toString(), ioe);
    }
    Reader reader = null;
    Writer writer = null;
    try {
      reader =
          (m_encoding == null)
              ? new FileReader(src)
              : new InputStreamReader(new FileInputStream(src), m_encoding);
      writer =
          (m_encoding == null)
              ? new FileWriter(temp)
              : new OutputStreamWriter(new FileOutputStream(temp), m_encoding);
      BufferedReader br = new BufferedReader(reader);
      BufferedWriter bw = new BufferedWriter(writer);
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
      if (m_token != null) {
        final String val = stringReplace(m_value.getText(), "\n", LINE_SEPARATOR);
        final String tok = stringReplace(m_token.getText(), "\n", LINE_SEPARATOR);
        getLogger()
            .debug(
                (((("Replacing in " + src.getPath()) + ": ") + m_token.getText()) + " --> ")
                    + m_value.getText());
        newString = stringReplace(newString, tok, val);
      }
      if (m_replacefilters.size() > 0) {
        newString = processReplacefilters(newString, src.getPath());
      }
      boolean changes = !newString.equals(buf);
      if (changes) {
        bw.write(newString, 0, newString.length());
        bw.flush();
      }
      bw.close();
      writer = null;
      br.close();
      reader = null;
      if (changes) {
        ++m_fileCount;
        src.delete();
        temp.renameTo(src);
        temp = null;
      }
    } catch (IOException ioe) {
      throw new TaskException(
          (((("IOException in " + src) + " - ") + ioe.getClass().getName()) + ":")
              + ioe.getMessage(),
          ioe);
    } finally {
      IOUtil.shutdownReader(reader);
      IOUtil.shutdownWriter(writer);
      if (temp != null) {
        temp.delete();
      }
    }
  }
}
