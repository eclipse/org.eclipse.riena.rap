Getting started with Riena on RAP

1. [Tooling]
   Install the Rich Ajax Platform (RAP) Tools
   Help -> Install New Software...

2. [Target platform]
   Use RAP 1.5 or later as your target.
   http://eclipse.org/rap/downloads/
   
   Add the Equinox SDK to the target platform.
   http://download.eclipse.org/equinox/

3. [Projects]
   Clone the Riena repository - git://dev.eclipse.org/gitroot/riena/org.eclipse.riena.git
   
   Clone the Riena RAP repository - git://dev.eclipse.org/gitroot/riena/org.eclipse.riena.rap.git
   
   Import org.eclipse.riena.releng.rap/riena_on_rap-pserver.psf
   CVS User: anonymous
   CVS Password: <blank>
   (CVS via pserver may not work if you are behind a corporate proxy / firewall)

4. There should be (almost) no compiler errors at this point.

5. Launch 'RAP Mail Sample (RCP UI)' in
   org.eclipse.riena.sample.app.client.rcpmail

6. Launch some of the other 'RAP .....launch' configurations in the other
   examples.
   
   
   [Important]
   Do not use "Add required bundles" since it may add some RCP bundles, which are not desired in a RAP launch configuration.
