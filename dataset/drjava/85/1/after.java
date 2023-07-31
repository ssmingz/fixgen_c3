class PlaceHold {
  public final void methodDeclaratorLookahead() throws ParseException {
    jj_consume_token(IDENTIFIER);
    formalParametersLookahead();
    label_63:
    while (true) {
      switch (jj_ntk == (-1) ? jj_ntk() : jj_ntk) {
        case LBRACKET:
          break;
        default:
          jj_la1[186] = jj_gen;
          break label_63;
      }
      jj_consume_token(LBRACKET);
      jj_consume_token(RBRACKET);
    }
  }
}
