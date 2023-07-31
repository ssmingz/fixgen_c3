class PlaceHold {
  void createColorGroup() {
    colorGroup = new Group(controlGroup, SWT.NULL);
    colorGroup.setLayout(new GridLayout(2, false));
    colorGroup.setLayoutData(
        new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
    colorGroup.setText(ControlExample.getResourceString("Colors"));
    new Label(colorGroup, SWT.NONE).setText(ControlExample.getResourceString("Foreground_Color"));
    foregroundButton = new Button(colorGroup, SWT.PUSH);
    new Label(colorGroup, SWT.NONE).setText(ControlExample.getResourceString("Background_Color"));
    backgroundButton = new Button(colorGroup, SWT.PUSH);
    fontButton = new Button(colorGroup, SWT.PUSH);
    fontButton.setText(ControlExample.getResourceString("Font"));
    Shell shell = backgroundButton.getShell();
    final ColorDialog backgroundDialog = new ColorDialog(shell);
    final ColorDialog foregroundDialog = new ColorDialog(shell);
    final FontDialog fontDialog = new FontDialog(shell);
    int imageSize = 12;
    Display display = shell.getDisplay();
    final Image backgroundImage = new Image(display, imageSize, imageSize);
    final Image foregroundImage = new Image(display, imageSize, imageSize);
    backgroundButton.setImage(backgroundImage);
    backgroundButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            RGB rgb = backgroundDialog.open();
            if (rgb == null) {
              return;
            }
            Color oldColor = backgroundColor;
            backgroundColor = new Color(backgroundButton.getDisplay(), rgb);
            setExampleWidgetBackground();
            if (oldColor != null) {
              oldColor.dispose();
            }
          }
        });
    foregroundButton.setImage(foregroundImage);
    foregroundButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent event) {
            RGB rgb = foregroundDialog.open();
            if (rgb == null) {
              return;
            }
            Color oldColor = foregroundColor;
            foregroundColor = new Color(foregroundButton.getDisplay(), rgb);
            setExampleWidgetForeground();
            if (oldColor != null) {
              oldColor.dispose();
            }
          }
        });
    fontButton.addSelectionListener(
        new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
            FontData fontData = fontDialog.open();
            if (fontData == null) {
              return;
            }
            Font oldFont = font;
            font = new Font(fontButton.getDisplay(), fontData);
            setExampleWidgetFont();
            setExampleWidgetSize();
            if (oldFont != null) {
              oldFont.dispose();
            }
          }
        });
    backgroundButton.addDisposeListener(
        new DisposeListener() {
          public void widgetDisposed(DisposeEvent event) {
            if (backgroundImage != null) {
              backgroundImage.dispose();
            }
            if (foregroundImage != null) {
              foregroundImage.dispose();
            }
            if (backgroundColor != null) {
              backgroundColor.dispose();
            }
            if (foregroundColor != null) {
              foregroundColor.dispose();
            }
            if (font != null) {
              font.dispose();
            }
            backgroundColor = null;
            foregroundColor = null;
            font = null;
          }
        });
  }
}
