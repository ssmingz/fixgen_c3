class PlaceHold {
  public void buildTree(int indentLevel) {
    char[] indent = new char[indentLevel];
    Arrays.fill(indent, ' ');
    String oneLevel = new String(indent);
    boolean autoCloseComments = DrJava.getConfig().getSetting(AUTO_CLOSE_COMMENTS).booleanValue();
    IndentRule rule37 = new ActionStartCurrStmtPlus(oneLevel);
    IndentRule rule36 = new ActionStartStmtOfBracePlus(oneLevel);
    IndentRule rule35 = rule37;
    IndentRule rule34 = new QuestionExistsCharInStmt('?', ':', rule35, rule36);
    IndentRule rule33 = new QuestionLineContains(':', rule34, rule37);
    IndentRule rule32 = new ActionStartCurrStmtPlus("");
    IndentRule rule31 = new QuestionCurrLineStartsWithSkipComments("{", rule32, rule33);
    IndentRule rule39 = new ActionStartPrevStmtPlus("", true);
    IndentRule rule29 = rule36;
    IndentRule rule28 = new ActionStartPrevStmtPlus("", false);
    IndentRule rule40 = rule28;
    IndentRule rule30 = new QuestionExistsCharInPrevStmt('?', rule40, rule39);
    IndentRule rule27 = new QuestionExistsCharInStmt('?', ':', rule28, rule29);
    IndentRule rule26 = new QuestionLineContains(':', rule27, rule30);
    IndentRule rule25 = new QuestionStartingNewStmt(rule26, rule31);
    IndentRule rule24 = rule25;
    IndentRule rule23 = rule36;
    IndentRule rule22 =
        new QuestionHasCharPrecedingOpenBrace(new char[] {'=', ',', '{'}, rule23, rule24);
    IndentRule rule21 = rule36;
    IndentRule rule20 = new QuestionStartAfterOpenBrace(rule21, rule22);
    IndentRule rule19 = new ActionStartStmtOfBracePlus("");
    IndentRule rule18 = new QuestionCurrLineStartsWithSkipComments("}", rule19, rule20);
    IndentRule rule17 = new QuestionBraceIsCurly(rule18, rule25);
    IndentRule rule16 = new ActionBracePlus(" " + oneLevel);
    IndentRule rule15 = new ActionBracePlus(" ");
    IndentRule rule38 = new QuestionCurrLineStartsWith(")", rule30, rule15);
    IndentRule rule14 = new QuestionNewParenPhrase(rule38, rule16);
    IndentRule rule13 = new QuestionBraceIsParenOrBracket(rule14, rule17);
    IndentRule rule12 = new ActionStartPrevLinePlus("");
    IndentRule rule11 = rule12;
    IndentRule rule10 = new ActionStartPrevLinePlus("* ");
    IndentRule rule09 = new QuestionCurrLineEmptyOrEnterPress(rule10, rule11);
    IndentRule rule08 = rule12;
    IndentRule rule07 = new QuestionCurrLineStartsWith("*", rule08, rule09);
    IndentRule rule06 = new QuestionPrevLineStartsWith("*", rule07, rule12);
    IndentRule rule05 = new ActionStartPrevLinePlus(" ");
    IndentRule rule04 = new ActionStartPrevLinePlus(" * ");
    IndentRule rule46 = new ActionStartPrevLinePlus("  * ");
    IndentRule rule47 = new ActionStartPrevLinePlus("  ");
    IndentRule rule48 = new QuestionPrevLineStartsJavaDocWithText(rule47, rule05);
    IndentRule rule41 =
        new ActionStartPrevLinePlusMultilinePreserve(new String[] {" * \n", " */"}, 0, 3, 0, 3);
    IndentRule rule45 = new QuestionPrevLineStartsJavaDocWithText(rule46, rule04);
    IndentRule rule42 = new QuestionFollowedByStar(rule04, rule41);
    IndentRule rule49 =
        new ActionStartPrevLinePlusMultilinePreserve(new String[] {"  */"}, 0, 4, 0, 4);
    IndentRule rule50 = new QuestionFollowedByStar(rule46, rule49);
    IndentRule rule51 = new QuestionPrevLineStartsJavaDocWithText(rule50, rule42);
    IndentRule rule03 =
        new QuestionCurrLineEmptyOrEnterPress(autoCloseComments ? rule51 : rule45, rule48);
    IndentRule rule02 = new QuestionPrevLineStartsComment(rule03, rule06);
    IndentRule rule43 = new ActionDoNothing();
    IndentRule rule44 = new QuestionCurrLineIsWingComment(rule43, rule13);
    IndentRule rule01 = new QuestionInsideComment(rule02, rule44);
    _topRule = rule01;
  }
}
