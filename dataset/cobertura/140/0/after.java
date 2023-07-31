class PlaceHold {
  public LineData getLineCoverage(int lineNumber) {
    lock.lock();
    try {
      Iterator iter = this.children.values().iterator();
      while (iter.hasNext()) {
        ClassData classData = ((ClassData) (iter.next()));
        if (classData.isValidSourceLineNumber(lineNumber)) {
          return classData.getLineCoverage(lineNumber);
        }
      }
    } finally {
      lock.unlock();
    }
    return null;
  }
}
