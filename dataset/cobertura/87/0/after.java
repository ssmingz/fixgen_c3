class PlaceHold {
  public final void UnmodifiedClassDeclaration() throws ParseException {
    String sOldClass = _sClass;
    int oldNcss = _ncss;
    int oldFunctions = _functions;
    int oldClasses = _classes;
    if (!_sClass.equals("")) {
      _sClass += ".";
    }
    _sClass += getToken(2).image;
    _classLevel++;
    Modifiers();
    jj_consume_token(CLASS);
    Identifier();
    switch (jj_ntk == (-1) ? jj_ntk() : jj_ntk) {
      case LT:
        TypeParameters();
        break;
      default:
        jj_la1[19] = jj_gen;
    }
    switch (jj_ntk == (-1) ? jj_ntk() : jj_ntk) {
      case EXTENDS:
        jj_consume_token(EXTENDS);
        Name();
        switch (jj_ntk == (-1) ? jj_ntk() : jj_ntk) {
          case LT:
            TypeArguments();
            break;
          default:
            jj_la1[20] = jj_gen;
        }
        break;
      default:
        jj_la1[21] = jj_gen;
    }
    switch (jj_ntk == (-1) ? jj_ntk() : jj_ntk) {
      case IMPLEMENTS:
        jj_consume_token(IMPLEMENTS);
        NameList();
        break;
      default:
        jj_la1[22] = jj_gen;
    }
    ClassBody();
    _ncss++;
    Util.debug("_ncss++");
    _classLevel--;
    if (_classLevel == 0) {
      ObjectMetric metric = new ObjectMetric();
      metric.name = _sPackage + _sClass;
      metric.ncss = _ncss - oldNcss;
      metric.functions = _functions - oldFunctions;
      metric.classes = _classes - oldClasses;
      Token lastToken = getToken(0);
      _vClasses.add(metric);
      _pPackageMetric.functions += _functions - oldFunctions;
      _pPackageMetric.classes++;
      _pPackageMetric.javadocs += _javadocs;
    }
    _functions = oldFunctions;
    _classes = oldClasses + 1;
    _sClass = sOldClass;
  }
}
