class PlaceHold {
  void updateChevron() {
    if (control != null) {
      int width = itemBounds.width;
      if (((style & SWT.DROP_DOWN) != 0) && (width < preferredWidth)) {
        int height = Math.min(control.getSize().y, itemBounds.height);
        if (chevron == null) {
          chevron = new ToolBar(parent, SWT.FLAT | SWT.NO_FOCUS);
          ToolItem toolItem = new ToolItem(chevron, SWT.PUSH);
          if (height > 6) {
            toolItem.setImage(getArrowImage());
          }
          chevron.setBackground(parent.getBackground());
          toolItem.addListener(
              Selection,
              new Listener() {
                public void handleEvent(Event event) {
                  CoolItem.this.onSelection(event);
                }
              });
        }
        chevron.setBounds(
            (((itemBounds.x + width) - CHEVRON_LEFT_MARGIN) - CHEVRON_IMAGE_WIDTH)
                - CHEVRON_HORIZONTAL_TRIM,
            itemBounds.y + MARGIN_HEIGHT,
            CHEVRON_IMAGE_WIDTH + CHEVRON_HORIZONTAL_TRIM,
            height);
        chevron.setVisible(true);
      } else if (chevron != null) {
        chevron.setVisible(false);
      }
    }
  }
}
