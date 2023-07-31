class PlaceHold {
  protected PerformanceMeter createMeter(String id) {
    Performance performance = Performance.getDefault();
    String scenarioId = "org.eclipse.swt.test." + id;
    PerformanceMeter meter = performance.createPerformanceMeter(scenarioId);
    performance.tagAsSummary(meter, id, CPU_TIME);
    return meter;
  }
}
