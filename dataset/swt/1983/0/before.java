class PlaceHold {
  public void test_createComposites() {
    PerformanceMeter meter = createMeter("Create composites");
    int samples;
    Performance performance = Performance.getDefault();
    performance.tagAsGlobalSummary(meter, "Create composites", CPU_TIME);
    for (samples = 0; samples < 2; samples++) {
      Shell shell = new Shell(display);
      for (int i = 0; i < 100; i++) {
        Composite c = new Composite(shell, SWT.NONE);
        for (int j = 0; j < 10; j++) {
          Composite c2 = new Composite(c, SWT.NONE);
        }
      }
      shell.dispose();
      while (display.readAndDispatch())
        ;
    }
    for (samples = 0; samples < 100; samples++) {
      Shell shell = new Shell(display);
      meter.start();
      for (int i = 0; i < 100; i++) {
        Composite c = new Composite(shell, SWT.NONE);
        for (int j = 0; j < 50; j++) {
          Composite c2 = new Composite(c, SWT.NONE);
        }
      }
      meter.stop();
      shell.dispose();
      while (display.readAndDispatch())
        ;
    }
    disposeMeter(meter);
  }
}
