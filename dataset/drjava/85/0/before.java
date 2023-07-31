class PlaceHold {
  public final void nameListLookahead() throws ParseException {
    nameLookahead();
    label_59:
    while (true) {
      switch (jj_ntk == (-1) ? jj_ntk() : jj_ntk) {
        case COMMA:
          break;
        default:
          jj_la1[185] = jj_gen;
          break label_59;
      }
      jj_consume_token(COMMA);
      nameLookahead();
    }
  }
}
