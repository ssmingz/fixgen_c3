class PlaceHold {
  void startAnimationTimer() {
    final Display display = parent.getDisplay();
    display.timerExec(
        TIMER,
        new Runnable() {
          public void run() {
            if (canvas.isDisposed()) {
              return;
            }
            int timeout = TIMER;
            GraphicsTab tab = getTab();
            if (tab instanceof AnimatedGraphicsTab) {
              AnimatedGraphicsTab animTab = ((AnimatedGraphicsTab) (tab));
              if (animTab.getAnimation()) {
                Rectangle rect = canvas.getClientArea();
                animTab.next(rect.width, rect.height);
                canvas.redraw();
                canvas.update();
              }
              timeout = animTab.getSelectedAnimationTime();
            }
            display.timerExec(timeout, this);
          }
        });
  }
}
