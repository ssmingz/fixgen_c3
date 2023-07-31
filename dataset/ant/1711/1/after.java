class PlaceHold {
  protected void doReplace(File f, int options) throws IOException {
    File temp = FILE_UTILS.createTempFile("replace", ".txt", null, true, true);
    Reader r = null;
    Writer w = null;
    try {
      if (encoding == null) {
        r = new FileReader(f);
        w = new FileWriter(temp);
      } else {
        r = new InputStreamReader(new FileInputStream(f), encoding);
        w = new OutputStreamWriter(new FileOutputStream(temp), encoding);
      }
      BufferedReader br = new BufferedReader(r);
      BufferedWriter bw = new BufferedWriter(w);
      PrintWriter pw = new PrintWriter(bw);
      boolean changes = false;
      log(
          (((((((("Replacing pattern '" + regex.getPattern(getProject())) + "' with '")
                                      + subs.getExpression(getProject()))
                                  + "' in '")
                              + f.getPath())
                          + "'")
                      + (byline ? " by line" : ""))
                  + (flags.length() > 0 ? (" with flags: '" + flags) + "'" : ""))
              + ".",
          MSG_VERBOSE);
      if (byline) {
        StringBuffer linebuf = new StringBuffer();
        String line = null;
        String res = null;
        int c;
        boolean hasCR = false;
        do {
          c = br.read();
          if (c == '\r') {
            if (hasCR) {
              line = linebuf.toString();
              res = doReplace(regex, subs, line, options);
              if (!res.equals(line)) {
                changes = true;
              }
              pw.print(res);
              pw.print('\r');
              linebuf = new StringBuffer();
            } else {
              hasCR = true;
            }
          } else if (c == '\n') {
            line = linebuf.toString();
            res = doReplace(regex, subs, line, options);
            if (!res.equals(line)) {
              changes = true;
            }
            pw.print(res);
            if (hasCR) {
              pw.print('\r');
              hasCR = false;
            }
            pw.print('\n');
            linebuf = new StringBuffer();
          } else {
            if (hasCR || (c < 0)) {
              line = linebuf.toString();
              res = doReplace(regex, subs, line, options);
              if (!res.equals(line)) {
                changes = true;
              }
              pw.print(res);
              if (hasCR) {
                pw.print('\r');
                hasCR = false;
              }
              linebuf = new StringBuffer();
            }
            if (c >= 0) {
              linebuf.append(((char) (c)));
            }
          }
        } while (c >= 0);
        pw.flush();
      } else {
        String buf = FileUtils.safeReadFully(br);
        String res = doReplace(regex, subs, buf, options);
        if (!res.equals(buf)) {
          changes = true;
        }
        pw.print(res);
        pw.flush();
      }
      r.close();
      r = null;
      w.close();
      w = null;
      if (changes) {
        log("File has changed; saving the updated file", MSG_VERBOSE);
        try {
          long origLastModified = f.lastModified();
          FILE_UTILS.rename(temp, f);
          if (preserveLastModified) {
            FILE_UTILS.setFileLastModified(f, origLastModified);
          }
          temp = null;
        } catch (IOException e) {
          throw new BuildException("Couldn't rename temporary file " + temp, e, getLocation());
        }
      } else {
        log("No change made", MSG_DEBUG);
      }
    } finally {
      FileUtils.close(r);
      FileUtils.close(w);
      if (temp != null) {
        temp.delete();
      }
    }
  }
}
