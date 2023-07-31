class PlaceHold {
  void createExampleWidgets() {
    int style = getDefaultStyle();
    int itemStyle = 0;
    int toolBarStyle = SWT.FLAT;
    if (borderButton.getSelection()) {
      style |= SWT.BORDER;
    }
    if (flatButton.getSelection()) {
      style |= SWT.FLAT;
    }
    if (dropDownButton.getSelection()) {
      itemStyle |= SWT.DROP_DOWN;
    }
    coolBar = new CoolBar(coolBarGroup, style);
    ToolBar toolBar = new ToolBar(coolBar, toolBarStyle);
    ToolItem item = new ToolItem(toolBar, SWT.PUSH);
    item.setImage(instance.images[ControlExample.ciClosedFolder]);
    item.setToolTipText("SWT.PUSH");
    item = new ToolItem(toolBar, SWT.PUSH);
    item.setImage(instance.images[ControlExample.ciOpenFolder]);
    item.setToolTipText("SWT.PUSH");
    item = new ToolItem(toolBar, SWT.PUSH);
    item.setImage(instance.images[ControlExample.ciTarget]);
    item.setToolTipText("SWT.PUSH");
    item = new ToolItem(toolBar, SWT.SEPARATOR);
    item = new ToolItem(toolBar, SWT.PUSH);
    item.setImage(instance.images[ControlExample.ciClosedFolder]);
    item.setToolTipText("SWT.PUSH");
    item = new ToolItem(toolBar, SWT.PUSH);
    item.setImage(instance.images[ControlExample.ciOpenFolder]);
    item.setToolTipText("SWT.PUSH");
    pushItem = new CoolItem(coolBar, itemStyle);
    pushItem.setControl(toolBar);
    Point pushSize = toolBar.computeSize(DEFAULT, DEFAULT);
    pushSize = pushItem.computeSize(pushSize.x, pushSize.y);
    pushItem.setSize(pushSize);
    pushItem.setMinimumSize(item.getWidth(), pushSize.y);
    pushItem.addSelectionListener(new CoolItemSelectionListener());
    toolBar = new ToolBar(coolBar, toolBarStyle);
    item = new ToolItem(toolBar, SWT.DROP_DOWN);
    item.setImage(instance.images[ControlExample.ciOpenFolder]);
    item.setToolTipText("SWT.DROP_DOWN");
    item.addSelectionListener(new DropDownSelectionListener());
    item = new ToolItem(toolBar, SWT.DROP_DOWN);
    item.setImage(instance.images[ControlExample.ciClosedFolder]);
    item.setToolTipText("SWT.DROP_DOWN");
    item.addSelectionListener(new DropDownSelectionListener());
    dropDownItem = new CoolItem(coolBar, itemStyle);
    dropDownItem.setControl(toolBar);
    Point dropSize = toolBar.computeSize(DEFAULT, DEFAULT);
    dropSize = dropDownItem.computeSize(dropSize.x, dropSize.y);
    dropDownItem.setSize(dropSize);
    dropDownItem.setMinimumSize(item.getWidth(), dropSize.y);
    dropDownItem.addSelectionListener(new CoolItemSelectionListener());
    toolBar = new ToolBar(coolBar, toolBarStyle);
    item = new ToolItem(toolBar, SWT.RADIO);
    item.setImage(instance.images[ControlExample.ciClosedFolder]);
    item.setToolTipText("SWT.RADIO");
    item = new ToolItem(toolBar, SWT.RADIO);
    item.setImage(instance.images[ControlExample.ciClosedFolder]);
    item.setToolTipText("SWT.RADIO");
    item = new ToolItem(toolBar, SWT.RADIO);
    item.setImage(instance.images[ControlExample.ciClosedFolder]);
    item.setToolTipText("SWT.RADIO");
    radioItem = new CoolItem(coolBar, itemStyle);
    radioItem.setControl(toolBar);
    Point radioSize = toolBar.computeSize(DEFAULT, DEFAULT);
    radioSize = radioItem.computeSize(radioSize.x, radioSize.y);
    radioItem.setSize(radioSize);
    radioItem.setMinimumSize(item.getWidth(), radioSize.y);
    radioItem.addSelectionListener(new CoolItemSelectionListener());
    toolBar = new ToolBar(coolBar, toolBarStyle);
    item = new ToolItem(toolBar, SWT.CHECK);
    item.setImage(instance.images[ControlExample.ciClosedFolder]);
    item.setToolTipText("SWT.CHECK");
    item = new ToolItem(toolBar, SWT.CHECK);
    item.setImage(instance.images[ControlExample.ciTarget]);
    item.setToolTipText("SWT.CHECK");
    item = new ToolItem(toolBar, SWT.CHECK);
    item.setImage(instance.images[ControlExample.ciOpenFolder]);
    item.setToolTipText("SWT.CHECK");
    item = new ToolItem(toolBar, SWT.CHECK);
    item.setImage(instance.images[ControlExample.ciTarget]);
    item.setToolTipText("SWT.CHECK");
    checkItem = new CoolItem(coolBar, itemStyle);
    checkItem.setControl(toolBar);
    Point checkSize = toolBar.computeSize(DEFAULT, DEFAULT);
    checkSize = checkItem.computeSize(checkSize.x, checkSize.y);
    checkItem.setSize(checkSize);
    checkItem.setMinimumSize(item.getWidth(), checkSize.y);
    checkItem.addSelectionListener(new CoolItemSelectionListener());
    if (order != null) {
      coolBar.setItemLayout(order, wrapIndices, sizes);
      pushItem.setPreferredSize(pushSize);
      dropDownItem.setPreferredSize(dropSize);
      radioItem.setPreferredSize(radioSize);
      checkItem.setPreferredSize(checkSize);
    } else {
      coolBar.setWrapIndices(new int[] {1, 3});
    }
    coolBar.addListener(
        Resize,
        new Listener() {
          public void handleEvent(Event event) {
            exampleGroup.layout();
          }
        });
  }
}
