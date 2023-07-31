class TableTree {
  public TableTree(Composite parent, int style) {
    super(parent, NONE);
    table = new Table(this, style);
    Listener tableListener =
        new Listener() {
          public void handleEvent(Event e) {
            switch (e.type) {
              case SWT.MouseDown:
                onMouseDown(e);
                break;
              case SWT.Selection:
                onSelection(e);
                break;
              case SWT.DefaultSelection:
                onSelection(e);
                break;
              case SWT.KeyDown:
                onKeyDown(e);
                break;
            }
          }
        };
    int[] tableEvents = new int[] {SWT.MouseDown, SWT.Selection, SWT.DefaultSelection, SWT.KeyDown};
    for (int i = 0; i < tableEvents.length; i++) {
      table.addListener(tableEvents[i], tableListener);
    }
    Listener listener =
        new Listener() {
          public void handleEvent(Event e) {
            switch (e.type) {
              case SWT.Dispose:
                onDispose(e);
                break;
              case SWT.Resize:
                onResize(e);
                break;
              case SWT.FocusIn:
                onFocusIn(e);
                break;
            }
          }
        };
    int[] events = new int[] {SWT.Dispose, SWT.Resize, SWT.FocusIn};
    for (int i = 0; i < events.length; i++) {
      addListener(events[i], listener);
    }
  }
}
