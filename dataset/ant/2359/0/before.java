class PlaceHold {
  public void testOnErrorReport() {
    expectLogContaining("onerror.report", "MyTaskNotPresent cannot be found");
  }
}
