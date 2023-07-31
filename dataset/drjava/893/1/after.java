class JavaCCParser {
  public JavaCCParser(InputStream is, File f) {
    _parser = new Parser(is);
    _parser.setFile(f);
    _f = f;
  }
}
