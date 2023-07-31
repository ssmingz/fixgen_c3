class PlaceHold {
  public boolean isValidSourceLineNumber(int lineNumber) {
    Iterator iter = this.children.values().iterator();
    while (iter.hasNext()) {
      ClassData classData = ((ClassData) (iter.next()));
      if (classData.isValidSourceLineNumber(lineNumber)) {
        return true;
      }
    }
    return false;
  }
}
