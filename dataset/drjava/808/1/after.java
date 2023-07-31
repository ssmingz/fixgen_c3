class PlaceHold {
  public void testSwitchStatement() throws ParseException {
    List<SwitchBlock> cases = new LinkedList<SwitchBlock>();
    List<Node> stmts1 = new LinkedList<Node>();
    stmts1.add(
        new ExpressionStatement(
            new ObjectMethodCall(new AmbiguousName("o"), "m", null, "", -1, -1, -1, -1), true));
    stmts1.add(new BreakStatement(null));
    List<Node> stmts2 = new LinkedList<Node>();
    stmts2.add(new EmptyStatement());
    List<Node> stmts3 = new LinkedList<Node>();
    stmts3.add(new BreakStatement(null));
    cases.add(new SwitchBlock(new IntegerLiteral("0"), stmts1));
    cases.add(new SwitchBlock(new IntegerLiteral("1"), stmts2));
    cases.add(new SwitchBlock(new IntegerLiteral("2"), stmts3));
    expectedAST.add(new SwitchStatement(new AmbiguousName("i"), cases, "", -1, -1, -1, -1));
    verifyOutput("switch( i ) { case 0: o.m(); break; case 1: ; case 2: break; }", expectedAST);
  }
}
