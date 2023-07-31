class PlaceHold {
  public final ExpressionSuffix primarySuffix() throws ParseException {
    ExpressionSuffix es;
    if (jj_2_29(2)) {
      es = dotThis();
    } else if (jj_2_30(2)) {
      es = dotAllocationExpression();
    } else {
      switch (jj_ntk == (-1) ? jj_ntk() : jj_ntk) {
        case LBRACKET:
          es = arrayReference();
          break;
        case DOT:
          es = dotIdentifier();
          break;
        case LPAREN:
          es = arguments();
          break;
        default:
          jj_la1[98] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
      }
    }
    {
      if (true) {
        return es;
      }
    }
    throw new Error("Missing return statement in function");
  }
}
