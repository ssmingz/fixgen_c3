class PlaceHold {
  public void assignCounterIds() {
    AtomicInteger idGenerator = new AtomicInteger(0);
    for (List<TouchPointDescriptor> tpd : currentLine2touchPoints.values()) {
      for (TouchPointDescriptor t : tpd) {
        t.assignCounters(idGenerator);
      }
    }
    maxCounterId = idGenerator.get();
  }
}
