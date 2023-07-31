class PlaceHold {
  private synchronized String replaceTokens(String line, String parent) throws BuildException {
    String beginToken = getBeginToken();
    String endToken = getEndToken();
    if (recurseDepth == 0) {
      passedTokens = new VectorSet();
    }
    recurseDepth++;
    if (passedTokens.contains(parent) && (!duplicateToken)) {
      duplicateToken = true;
      System.out.println(
          (((((((("Infinite loop in tokens. Currently known tokens : " + passedTokens.toString())
                                          + "\nProblem token : ")
                                      + beginToken)
                                  + parent)
                              + endToken)
                          + " called from ")
                      + beginToken)
                  + passedTokens.lastElement().toString())
              + endToken);
      recurseDepth--;
      return parent;
    }
    passedTokens.addElement(parent);
    String value = iReplaceTokens(line);
    if (((value.indexOf(beginToken) == (-1)) && (!duplicateToken)) && (recurseDepth == 1)) {
      passedTokens = null;
    } else if (duplicateToken) {
      if (passedTokens.size() > 0) {
        value = ((String) (passedTokens.remove(passedTokens.size() - 1)));
        if (passedTokens.size() == 0) {
          value = (beginToken + value) + endToken;
          duplicateToken = false;
        }
      }
    } else if (passedTokens.size() > 0) {
      passedTokens.remove(passedTokens.size() - 1);
    }
    recurseDepth--;
    return value;
  }
}
