class Configuration {
  public Configuration(Properties aProps, PrintStream aLog)
      throws RESyntaxException, FileNotFoundException, IOException {
    setPatternProperty(aProps, TODO_PATTERN_PROP, TODO_PATTERN);
    setPatternProperty(aProps, PARAMETER_PATTERN_PROP, PARAMETER_PATTERN);
    setPatternProperty(aProps, STATIC_PATTERN_PROP, STATIC_PATTERN);
    setPatternProperty(aProps, CONST_PATTERN_PROP, CONST_PATTERN);
    setPatternProperty(aProps, MEMBER_PATTERN_PROP, MEMBER_PATTERN);
    setPatternProperty(aProps, PUBLIC_MEMBER_PATTERN_PROP, PUBLIC_MEMBER_PATTERN);
    setPatternProperty(aProps, TYPE_PATTERN_PROP, TYPE_PATTERN);
    setPatternProperty(aProps, LOCAL_VAR_PATTERN_PROP, LOCAL_VAR_PATTERN);
    setPatternProperty(aProps, METHOD_PATTERN_PROP, METHOD_PATTERN);
    setPatternProperty(aProps, IGNORE_LINE_LENGTH_PATTERN_PROP, IGNORE_LINE_LENGTH_PATTERN);
    setIntProperty(aProps, aLog, MAX_LINE_LENGTH_PROP, MAX_LINE_LENGTH);
    setIntProperty(aProps, aLog, MAX_METHOD_LENGTH_PROP, MAX_METHOD_LENGTH);
    setIntProperty(aProps, aLog, MAX_CONSTRUCTOR_LENGTH_PROP, MAX_CONSTRUCTOR_LENGTH);
    setIntProperty(aProps, aLog, MAX_FILE_LENGTH_PROP, MAX_FILE_LENGTH);
    setBooleanProperty(aProps, ALLOW_TABS_PROP);
    setIntProperty(aProps, aLog, TAB_WIDTH_PROP, TAB_WIDTH);
    setBooleanProperty(aProps, ALLOW_PROTECTED_PROP);
    setBooleanProperty(aProps, ALLOW_PACKAGE_PROP);
    setBooleanProperty(aProps, ALLOW_NO_AUTHOR_PROP);
    setJavadocScope(
        Scope.getInstance(aProps.getProperty(JAVADOC_CHECKSCOPE_PROP, PRIVATE.getName())));
    setBooleanProperty(aProps, REQUIRE_PACKAGE_HTML_PROP);
    setBooleanProperty(aProps, IGNORE_IMPORTS_PROP);
    setIllegalImports(aProps.getProperty(ILLEGAL_IMPORTS_PROP, ILLEGAL_IMPORTS));
    setIllegalInstantiations(
        aProps.getProperty(ILLEGAL_INSTANTIATIONS_PROP, ILLEGAL_INSTANTIATIONS));
    setBooleanProperty(aProps, IGNORE_WHITESPACE_PROP);
    setBooleanProperty(aProps, IGNORE_CAST_WHITESPACE_PROP);
    setBooleanProperty(aProps, IGNORE_OP_WRAP_PROP);
    setBooleanProperty(aProps, IGNORE_BRACES_PROP);
    setBooleanProperty(aProps, IGNORE_LONG_ELL_PROP);
    setBooleanProperty(aProps, IGNORE_PUBLIC_IN_INTERFACE_PROP);
    setCacheFile(aProps.getProperty(CACHE_FILE_PROP));
    setBooleanProperty(aProps, IGNORE_IMPORT_LENGTH_PROP);
    setHeaderIgnoreLines(aProps.getProperty(HEADER_IGNORE_LINE_PROP));
    setBooleanProperty(aProps, HEADER_LINES_REGEXP_PROP);
    setBooleanProperty(aProps, JAVADOC_CHECK_UNUSED_THROWS_PROP);
    final String fname = aProps.getProperty(HEADER_FILE_PROP);
    if (fname != null) {
      setHeaderFile(fname);
    }
    setLeftCurlyOptionProperty(aProps, LCURLY_METHOD_PROP, aLog);
    setLeftCurlyOptionProperty(aProps, LCURLY_TYPE_PROP, aLog);
    setLeftCurlyOptionProperty(aProps, LCURLY_OTHER_PROP, aLog);
    setRCurly(getRightCurlyOptionProperty(aProps, RCURLY_PROP, SAME, aLog));
    setBlockOptionProperty(aProps, TRY_BLOCK_PROP, aLog);
    setBlockOptionProperty(aProps, CATCH_BLOCK_PROP, aLog);
    setBlockOptionProperty(aProps, FINALLY_BLOCK_PROP, aLog);
    setParenPadOption(getPadOptionProperty(aProps, PAREN_PAD_PROP, NOSPACE, aLog));
    setBasedir(aProps.getProperty(BASEDIR_PROP));
  }
}
