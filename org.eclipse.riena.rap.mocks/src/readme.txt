This fragment only exists for supressing two errors about missing extension point
in the workspace (ie during development time).

It provides dummy schema files for the following extension points, which are
not yet supported by RAP:

- org.eclipse.ui.bindings
- org.eclipse.ui.splashHandlers

There is no need to ship this fragment with applications, as the plugin.xml is not 
checked at runtime.