class LaunchConfigurationWorkingCopy {
  protected LaunchConfigurationWorkingCopy(
      org.eclipse.core.resources.IContainer container,
      String name,
      org.eclipse.debug.core.ILaunchConfigurationType type) {
    super(name, container);
    setInfo(new org.eclipse.debug.internal.core.LaunchConfigurationInfo());
    getInfo().setType(type);
    fSuppressChange = false;
  }
}
