class PlaceHold {
  int get_newText(int pNewText) {
    if (DEBUG) {
      print(this + ".IAccessibleText::get_newText");
    }
    String text = null;
    int start = 0;
    int end = 0;
    if (textInserted != null) {
      text = ((String) (textInserted[3]));
      start = ((Integer) (textInserted[1])).intValue();
      end = ((Integer) (textInserted[2])).intValue();
    }
    setString(pNewText, text);
    COM.MoveMemory(pNewText + OS.PTR_SIZEOF, new int[] {start}, 4);
    COM.MoveMemory((pNewText + OS.PTR_SIZEOF) + 4, new int[] {end}, 4);
    if (textInserted == null) {
      return COM.S_FALSE;
    }
    return COM.S_OK;
  }
}
