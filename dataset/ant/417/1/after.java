class PlaceHold {
  public File resolveFile(File file, String filename) throws TaskException {
    filename = filename.replace('/', File.separatorChar).replace('\\', File.separatorChar);
    if (filename.startsWith(File.separator)
        || (((filename.length() >= 2) && Character.isLetter(filename.charAt(0)))
            && (filename.charAt(1) == ':'))) {
      return normalize(filename);
    }
    if (file == null) {
      return new File(filename);
    }
    File helpFile = new File(file.getAbsolutePath());
    StringTokenizer tok = new StringTokenizer(filename, File.separator);
    while (tok.hasMoreTokens()) {
      String part = tok.nextToken();
      if (part.equals("..")) {
        helpFile = getParentFile(helpFile);
        if (helpFile == null) {
          String msg =
              (("The file or path you specified (" + filename) + ") is invalid relative to ")
                  + file.getPath();
          throw new TaskException(msg);
        }
      } else if (part.equals(".")) {
      } else {
        helpFile = new File(helpFile, part);
      }
    }
    return new File(helpFile.getAbsolutePath());
  }
}
